package land.leets.Carrot.domain.post.service;

import static land.leets.Carrot.domain.post.exception.PostErrorMessage.LATEST_SNAPSHOT_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.PostErrorMessage.LOCATION_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.PostErrorMessage.NO_RECRUITING_POST;
import static land.leets.Carrot.domain.post.exception.PostErrorMessage.POST_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.PostErrorMessage.SEARCH_RESULT_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.PostErrorMessage.USER_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.PostErrorMessage.WORK_TYPE_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.PostErrorMessage.WROTE_POST_NOT_FOUND;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import land.leets.Carrot.domain.career.entity.WorkType;
import land.leets.Carrot.domain.career.repository.WorkTypeRepository;
import land.leets.Carrot.domain.image.service.S3ImageService;
import land.leets.Carrot.domain.location.entity.DetailArea;
import land.leets.Carrot.domain.location.repository.LocationRepository;
import land.leets.Carrot.domain.post.controller.SuccessMessage;
import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.domain.PostedPost;
import land.leets.Carrot.domain.post.domain.ShortPostData;
import land.leets.Carrot.domain.post.dto.request.GetPostedPostRequest;
import land.leets.Carrot.domain.post.dto.request.PostPostImageRequest;
import land.leets.Carrot.domain.post.dto.request.PostPostRequest;
import land.leets.Carrot.domain.post.dto.response.PostResponse;
import land.leets.Carrot.domain.post.dto.response.PostedPostResponse;
import land.leets.Carrot.domain.post.dto.response.ShortPostResponse;
import land.leets.Carrot.domain.post.entity.DayOfWeek;
import land.leets.Carrot.domain.post.entity.Post;
import land.leets.Carrot.domain.post.entity.PostImage;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import land.leets.Carrot.domain.post.exception.PostErrorMessage;
import land.leets.Carrot.domain.post.exception.PostException;
import land.leets.Carrot.domain.post.mapper.PostDataMapper;
import land.leets.Carrot.domain.post.mapper.PostSnapshotMapper;
import land.leets.Carrot.domain.post.repository.PostImageRepository;
import land.leets.Carrot.domain.post.repository.PostRepository;
import land.leets.Carrot.domain.post.repository.PostSnapshotRepository;
import land.leets.Carrot.domain.user.repository.CeoRepository;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostSnapshotRepository postSnapshotRepository;
    private final LocationRepository locationRepository;

    private final WorkTypeRepository workTypeRepository;
    private final CeoRepository ceoRepository;
    private final S3ImageService s3ImageService;
    private final PostImageRepository postImageRepository;

    private static final String POST_STATUS_RECRUITING = "recruiting";
    private static final String POST_STATUS_DELETED = "deleted";
    private static final String POST_STATUS_DONE = "done";

    public void saveNewPost(PostPostRequest postPostRequest) {
        Post post = new Post(ceoRepository.findById(postPostRequest.userId())
                .orElseThrow(() -> new PostException(USER_NOT_FOUND)), postPostRequest.storeName(),
                postPostRequest.workPlaceAddress(), LocalDateTime.now(), POST_STATUS_RECRUITING);
        Post savedPost = postRepository.save(post);

        PostSnapshot postSnapshot = getPostSnapshot(postPostRequest, savedPost.getPostId());

        PostSnapshot savedSnapshot = postSnapshotRepository.save(postSnapshot);

        savePostSnapshotImage(postPostRequest.postData().imageUrlList(), savedSnapshot);
    }

    //db에 링크 저장하는 로직
    private void savePostSnapshotImage(List<String> imageUrlList, PostSnapshot postSnapshot) {
        for (String image : imageUrlList) {
            PostImage postImage = new PostImage(image, postSnapshot);
            postImageRepository.save(postImage);
        }
    }

    //실제 이미지 저장해서 List<String> 가져오는 로직
    public List<String> getImageUrlList(PostPostImageRequest postPostImageRequest) {
        List<MultipartFile> imageList = postPostImageRequest.imageList();
        List<String> imageUrlList = new ArrayList<>();
        for (MultipartFile image : imageList) {
            String imageUrl = s3ImageService.uploadImage(image, "post-images");
            imageUrlList.add(imageUrl);
        }
        return imageUrlList;
    }


    private PostSnapshot getPostSnapshot(PostPostRequest postPostRequest, Long postId) {
        PostData postData = postPostRequest.postData();
        Integer doAreaId = getAreaId(postData.doName());
        Integer siAreaId = getAreaId(postData.siName());
        Integer detailAreaId = getAreaId(postData.detailName());

        Integer jobTypeId = Math.toIntExact(workTypeRepository.findByType(postData.workType())
                .orElseGet(() -> workTypeRepository.save(new WorkType(postData.workType())))
                .getId());

        PostSnapshot postSnapshot = PostDataMapper.postDataToPostSnapshot(postData, doAreaId, siAreaId, detailAreaId,
                jobTypeId, getPost(postId));
        return postSnapshot;
    }

    private Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new PostException(POST_NOT_FOUND));
    }

    @Transactional
    public void updatePost(Long postId, PostPostRequest postPostRequest) {
        //기존 snapshot isLastest false로 수정
        PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndIsLastestTrue(postId)
                .orElseThrow(() -> new PostException(LATEST_SNAPSHOT_NOT_FOUND));
        postSnapshot.setIsLastest(false);
        for (String day : postPostRequest.postData().workDays()) {
            postSnapshot.selectDay(DayOfWeek.valueOf(day));
        }
        postSnapshotRepository.save(postSnapshot);

        //PostSnapshot 생성해서 새 PostSnapshot 저장
        PostSnapshot newPostSnapshot = getPostSnapshot(postPostRequest, postId);
        PostSnapshot savedPostSnapshot = postSnapshotRepository.save(newPostSnapshot);

        savePostSnapshotImage(postPostRequest.postData().imageUrlList(), savedPostSnapshot);

    }

    public Integer getAreaId(String areaName) {
        DetailArea detailArea = locationRepository.findByName(areaName)
                .orElseGet(() -> locationRepository.save(new DetailArea(areaName)));
        return detailArea.getAreaId();
    }

    public ResponseDto<PostResponse> getDetailPost(Long postId) {
        //존재 여부 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostException(PostErrorMessage.POST_NOT_FOUND));
        //스냅샷 db에서 검색
        PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndIsLastestTrue(postId)
                .orElseThrow(() -> new PostException(LATEST_SNAPSHOT_NOT_FOUND));

        List<String> imageList = postImageRepository.findByPostSnapshotId(postSnapshot.getId())
                .stream()
                .map(image -> image.getImageUrl()).collect(Collectors.toList());

        String workType = getWorkTypeName(postSnapshot.getWorkTypeId());
        PostResponse postResponse = new PostResponse(postId, post.getCeo().getId(), post.getStoreName(),
                post.getWorkPlaceAddress(), post.getCeo().getCeoName(),
                PostSnapshotMapper.postSnaphotToPostData(postSnapshot, getAreaName(postSnapshot.getDoAreaId())
                        , getAreaName(postSnapshot.getSiAreaId()), getAreaName(postSnapshot.getDetailAreaId()),
                        postSnapshot.getSelectedDays(), workType, imageList));

        return new ResponseDto(SuccessMessage.GET_POST_DETAIL_SUCCESS.getCode(),
                SuccessMessage.GET_POST_DETAIL_SUCCESS.getMessage(), postResponse);
    }

    public ResponseDto<ShortPostResponse> getPostByKeywordSearch(String keyword) {
        List<PostSnapshot> postSnapshotList = postSnapshotRepository.findByKeywordAndIsLastestTrue(keyword)
                .orElseThrow(() -> new PostException(SEARCH_RESULT_NOT_FOUND));
        List<ShortPostData> shortPostDataList = new ArrayList<>();

        for (PostSnapshot postSnapshot : postSnapshotList) {
            Post post = postSnapshot.getPost();

            ShortPostData shortPostData = new ShortPostData(post.getPostId(), postSnapshot.getTitle(),
                    post.getStoreName(), getAreaName
                    (postSnapshot.getDetailAreaId()),
                    postSnapshot.getPayType(), (long) postSnapshot.getPay(), post.getStatus(),
                    getFirstImageUrl(postSnapshot.getId())
            );
            shortPostDataList.add(shortPostData);
        }
        return new ResponseDto(SuccessMessage.GET_POST_KEYWORD_SEARCH_SUCCESS.getCode(),
                SuccessMessage.GET_POST_KEYWORD_SEARCH_SUCCESS.getMessage(), new ShortPostResponse(shortPostDataList));

    }

    @Transactional
    public void updatePostStatusDelete(Long postId) {
        Post post = getPost(postId);
        post.setStatus(POST_STATUS_DELETED);
        postRepository.save(post);
    }

    @Transactional
    public void updatePostStatusDone(Long postId) {
        Post post = getPost(postId);
        post.setStatus(POST_STATUS_DONE);
        postRepository.save(post);
    }

    //홈 화면에서 간략한 게시글 데이터 리스트 조회
    public ResponseDto<ShortPostResponse> getShortPostData() {
        List<Post> postList = postRepository.findByStatus(POST_STATUS_RECRUITING)
                .orElseThrow(() -> new PostException(NO_RECRUITING_POST));
        List<ShortPostData> shortPostDataList = new ArrayList<>();
        for (Post post : postList) {
            PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndIsLastestTrue(post.getPostId())
                    .orElseThrow(() -> new PostException(LATEST_SNAPSHOT_NOT_FOUND));
            getFirstImageUrl(postSnapshot.getId());

            ShortPostData shortPostData = new ShortPostData(post.getPostId(), postSnapshot.getTitle(),
                    post.getStoreName(), getAreaName(
                    postSnapshot.getDetailAreaId()), postSnapshot.getPayType(), (long) postSnapshot.getPay(),
                    post.getStatus(),
                    getFirstImageUrl(postSnapshot.getId()));
            shortPostDataList.add(shortPostData);
        }
        return new ResponseDto(SuccessMessage.GET_POST_LIST_SHORT_DATA_VER.getCode(),
                SuccessMessage.GET_POST_LIST_SHORT_DATA_VER.getMessage(), shortPostDataList);
    }

    //유저가 작성한 게시글 조회
    public ResponseDto<PostedPostResponse> getPostedPostList(GetPostedPostRequest getPostedPostRequest) {
        List<Post> postedPostList = postRepository.findByWriterId(getPostedPostRequest.userId())
                .orElseThrow(() -> new PostException(WROTE_POST_NOT_FOUND));
        List<PostedPost> postedPostDataList = new ArrayList<>();
        for (Post post : postedPostList) {
            PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndIsLastestTrue(post.getPostId())
                    .orElseThrow(() -> new PostException(LATEST_SNAPSHOT_NOT_FOUND));
            PostedPost postedPost = new PostedPost(post.getPostId(), postSnapshot.getTitle(),
                    getAreaName(postSnapshot.getDetailAreaId()), post.getStatus().equals(POST_STATUS_RECRUITING),
                    getFirstImageUrl(postSnapshot.getId()));
            postedPostDataList.add(postedPost);
        }
        return new ResponseDto(SuccessMessage.GET_POSTED_POST_LIST_SUCCESS.getCode(),
                SuccessMessage.GET_POSTED_POST_LIST_SUCCESS.getMessage(), new PostedPostResponse(postedPostDataList));
    }

    private String getFirstImageUrl(Long postSnapshotId) {
        return postImageRepository.findByPostSnapshotId(postSnapshotId).get(0).getImageUrl();
    }


    public String getAreaName(Integer areaId) {
        return locationRepository.findById(areaId)
                .orElseThrow(() -> new PostException(LOCATION_NOT_FOUND))
                .getName();
    }

    public String getWorkTypeName(Integer workTypeId) {
        return workTypeRepository.findById(workTypeId)
                .orElseThrow(() -> new PostException(WORK_TYPE_NOT_FOUND))
                .getType();
    }
}
