package land.leets.Carrot.domain.post.service;

import static land.leets.Carrot.domain.post.exception.ErrorMessage.LATEST_SNAPSHOT_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.ErrorMessage.LOCATION_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.ErrorMessage.NO_RECRUITING_POST;
import static land.leets.Carrot.domain.post.exception.ErrorMessage.POST_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.ErrorMessage.SEARCH_RESULT_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.ErrorMessage.WORK_TYPE_NOT_FOUND;
import static land.leets.Carrot.domain.post.exception.ErrorMessage.WROTE_POST_NOT_FOUND;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import land.leets.Carrot.domain.career.entity.WorkType;
import land.leets.Carrot.domain.career.repository.WorkTypeRepository;
import land.leets.Carrot.domain.location.entity.DetailArea;
import land.leets.Carrot.domain.location.repository.LocationRepository;
import land.leets.Carrot.domain.post.controller.SuccessMessage;
import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.domain.PostedPost;
import land.leets.Carrot.domain.post.domain.ShortPostData;
import land.leets.Carrot.domain.post.dto.request.GetPostedPostRequest;
import land.leets.Carrot.domain.post.dto.request.PostDeleteRequest;
import land.leets.Carrot.domain.post.dto.request.PostPostRequest;
import land.leets.Carrot.domain.post.dto.response.PostResponse;
import land.leets.Carrot.domain.post.dto.response.PostedPostResponse;
import land.leets.Carrot.domain.post.dto.response.ShortPostResponse;
import land.leets.Carrot.domain.post.entity.Post;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import land.leets.Carrot.domain.post.exception.ErrorMessage;
import land.leets.Carrot.domain.post.mapper.PostDataMapper;
import land.leets.Carrot.domain.post.mapper.PostSnapshotMapper;
import land.leets.Carrot.domain.post.repository.PostRepository;
import land.leets.Carrot.domain.post.repository.PostSnapshotRepository;
import land.leets.Carrot.global.common.exception.BaseException;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostSnapshotRepository postSnapshotRepository;
    private final LocationRepository locationRepository;

    private final WorkTypeRepository workTypeRepository;

    private static final String POST_STATUS_RECRUITING = "recruiting";
    private static final String POST_STATUS_DELETED = "deleted";

    public void saveNewPost(PostPostRequest postPostRequest) {
        Post post = new Post(postPostRequest.userId(), postPostRequest.storeName(), LocalDateTime.now(),
                POST_STATUS_RECRUITING);
        Post savedPost = postRepository.save(post);

        PostSnapshot postSnapshot = getPostSnapshot(postPostRequest, savedPost.getPostId());

        postSnapshotRepository.save(postSnapshot);
    }

    private PostSnapshot getPostSnapshot(PostPostRequest postPostRequest, Long postId) {
        PostData postData = postPostRequest.postData();
        Integer doAreaId = getAreaId(postData.doName());
        Integer siAreaId = getAreaId(postData.siName());
        Integer detailAreaId = getAreaId(postData.detailName());

        Integer jobTypeId = Math.toIntExact(workTypeRepository.findByType(postData.workType())
                .orElse(workTypeRepository.save(new WorkType(postData.workType())))
                .getId());

        PostSnapshot postSnapshot = PostDataMapper.postDataToPostSnapshot(postData, doAreaId, siAreaId, detailAreaId,
                jobTypeId, postId);
        return postSnapshot;
    }

    @Transactional
    public void saveNewPostSnapshot(Long postId, PostPostRequest postPostRequest) {
        //기존 snapshot isLastest false로 수정
        PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndIsLastestTrue(postId)
                .orElseThrow(() -> new BaseException(LATEST_SNAPSHOT_NOT_FOUND.getCode(),
                        LATEST_SNAPSHOT_NOT_FOUND.getErrorMessage()));
        postSnapshot.setLastest(false);
        postSnapshotRepository.save(postSnapshot);

        //PostSnapshot 생성해서 새 PostSnapshot 저장
        PostSnapshot newPostSnapshot = getPostSnapshot(postPostRequest, postId);
        postSnapshotRepository.save(newPostSnapshot);
    }

    public Integer getAreaId(String areaName) {
        return locationRepository.findByName(areaName)
                .orElse(locationRepository.save(new DetailArea(areaName)))
                .getAreaId();
    }

    public ResponseDto<PostResponse> getPost(Long postId) {
        //존재 여부 조회
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new BaseException(ErrorMessage.POST_NOT_FOUND.getCode(),
                        ErrorMessage.POST_NOT_FOUND.getErrorMessage()));
        //스냅샷 db에서 검색
        PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndIsLastestTrue(postId)
                .orElseThrow(() -> new BaseException(LATEST_SNAPSHOT_NOT_FOUND));

        String workType = getWorkTypeName(postSnapshot.getWorkTypeId());
        PostResponse postResponse = new PostResponse(postId, post.getUserId(), post.getStoreName(),
                PostSnapshotMapper.postSnaphotToPostData(postSnapshot, getAreaName(postSnapshot.getDoAreaId())
                        , getAreaName(postSnapshot.getSiAreaId()), getAreaName(postSnapshot.getDetailAreaId()),
                        getWorkTypeName(postSnapshot.getWorkTypeId()), workType));

        return new ResponseDto(SuccessMessage.GET_POST_DETAIL_SUCCESS.getCode(),
                SuccessMessage.GET_POST_DETAIL_SUCCESS.getMessage(), postResponse);
    }

    public ResponseDto<ShortPostResponse> getPostByKeywordSearch(String keyword) {
        List<PostSnapshot> postSnapshotList = postSnapshotRepository.findByKeywordAndIsLastestTrue(keyword)
                .orElseThrow(() -> new BaseException(SEARCH_RESULT_NOT_FOUND));
        List<ShortPostData> shortPostDataList = new ArrayList<>();

        for (PostSnapshot postSnapshot : postSnapshotList) {
            Post post = postRepository.findById(postSnapshot.getPostId())
                    .orElseThrow(() -> new BaseException(POST_NOT_FOUND));
            ShortPostData shortPostData = new ShortPostData(postSnapshot.getTitle(), post.getStoreName(), getAreaName
                    (postSnapshot.getDetailAreaId()),
                    postSnapshot.getPayType(), (long) postSnapshot.getPay(), post.getStatus(), ""//TODO 이미지 작업 추후 진행 예정
            );
            shortPostDataList.add(shortPostData);
        }
        return new ResponseDto(SuccessMessage.GET_POST_KEYWORD_SEARCH_SUCCESS.getCode(),
                SuccessMessage.GET_POST_KEYWORD_SEARCH_SUCCESS.getMessage(), new ShortPostResponse(shortPostDataList));

    }

    @Transactional
    public void updatePostStatusDelete(PostDeleteRequest postDeleteRequest) {
        Post post = postRepository.findById(postDeleteRequest.postId())
                .orElseThrow(() -> new BaseException(POST_NOT_FOUND));
        post.setStatus(POST_STATUS_DELETED);
        postRepository.save(post);
    }

    //홈 화면에서 간략한 게시글 데이터 리스트 조회
    public ResponseDto<ShortPostResponse> getShortPostData() {
        List<Post> postList = postRepository.findByStatus(POST_STATUS_RECRUITING)
                .orElseThrow(() -> new BaseException(NO_RECRUITING_POST));
        List<ShortPostData> shortPostDataList = new ArrayList<>();
        for (Post post : postList) {
            PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndIsLastestTrue(post.getPostId())
                    .orElseThrow(() -> new BaseException(LATEST_SNAPSHOT_NOT_FOUND));
            ShortPostData shortPostData = new ShortPostData(postSnapshot.getTitle(), post.getStoreName(), getAreaName(
                    postSnapshot.getDetailAreaId()), postSnapshot.getPayType(), (long) postSnapshot.getPay(),
                    post.getStatus(),
                    "");    //TODO 이미지 관련 작업 차후 구현 예정
            shortPostDataList.add(shortPostData);
        }
        return new ResponseDto(SuccessMessage.GET_POST_LIST_SHORT_DATA_VER.getCode(),
                SuccessMessage.GET_POST_LIST_SHORT_DATA_VER.getMessage(), shortPostDataList);
    }

    //유저가 작성한 게시글 조회
    public ResponseDto<PostedPostResponse> getPostedPostList(GetPostedPostRequest getPostedPostRequest) {
        List<Post> postedPostList = postRepository.findByWriterId(getPostedPostRequest.userId())
                .orElseThrow(() -> new BaseException(WROTE_POST_NOT_FOUND));
        List<PostedPost> postedPostDataList = new ArrayList<>();
        for (Post post : postedPostList) {
            PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndIsLastestTrue(post.getPostId())
                    .orElseThrow(() -> new BaseException(LATEST_SNAPSHOT_NOT_FOUND));
            PostedPost postedPost = new PostedPost(post.getPostId(), postSnapshot.getTitle(),
                    getAreaName(postSnapshot.getDetailAreaId()), post.getStatus().equals(POST_STATUS_RECRUITING), "");
            postedPostDataList.add(postedPost);
        }
        return new ResponseDto(SuccessMessage.GET_POSTED_POST_LIST_SUCCESS.getCode(),
                SuccessMessage.GET_POSTED_POST_LIST_SUCCESS.getMessage(), new PostedPostResponse(postedPostDataList));
    }


    public String getAreaName(Integer areaId) {
        return locationRepository.findById(areaId)
                .orElseThrow(() -> new BaseException(LOCATION_NOT_FOUND))
                .getName();
    }

    public String getWorkTypeName(Integer workTypeId) {
        return workTypeRepository.findById(workTypeId)
                .orElseThrow(() -> new BaseException(WORK_TYPE_NOT_FOUND))
                .getType();
    }
}
