package land.leets.Carrot.domain.post.service;

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
    PostRepository postRepository;
    PostSnapshotRepository postSnapshotRepository;
    LocationRepository locationRepository;

    WorkTypeRepository workTypeRepository;

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
        PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndLastestTrue(postId)
                .orElseThrow();

        String workType = getWorkTypeString(postSnapshot);
        PostResponse postResponse = new PostResponse(postId, post.getUserId(), post.getStoreName(),
                PostSnapshotMapper.postSnaphotToPostData(postSnapshot, getAreaName(postSnapshot.getDoAreaId())
                        , getAreaName(postSnapshot.getSiAreaId()), getAreaName(postSnapshot.getDetailAreaId()),
                        getWorkTypeName(postSnapshot.getWorkTypeId()), workType));

        return new ResponseDto(SuccessMessage.GET_POST_DETAIL_SUCCESS.getCode(),
                SuccessMessage.GET_POST_DETAIL_SUCCESS.getMessage(), postResponse);
    }

    public ResponseDto<ShortPostResponse> getPostByKeywordSearch(String keyword) {
        List<PostSnapshot> postSnapshotList = postSnapshotRepository.findByKeywordAndLastestTrue(keyword)
                .orElseThrow();
        List<ShortPostData> shortPostDataList = new ArrayList<>();

        for (PostSnapshot postSnapshot : postSnapshotList) {
            Post post = postRepository.findById(postSnapshot.getPostId()).orElseThrow();
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
                .orElseThrow();
        post.setStatus(POST_STATUS_DELETED);
        postRepository.save(post);
    }

    //홈 화면에서 간략한 게시글 데이터 리스트 조회
    public ResponseDto<ShortPostResponse> getShortPostData() {
        List<Post> postList = postRepository.findByStatus(POST_STATUS_RECRUITING)
                .orElseThrow();
        List<ShortPostData> shortPostDataList = new ArrayList<>();
        for (Post post : postList) {
            PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndLastestTrue(post.getPostId())
                    .orElseThrow();
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
                .orElseThrow();
        List<PostedPost> postedPostDataList = new ArrayList<>();
        for (Post post : postedPostList) {
            PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndLastestTrue(post.getPostId())
                    .orElseThrow();
            PostedPost postedPost = new PostedPost(post.getPostId(), postSnapshot.getTitle(),
                    getAreaName(postSnapshot.getDetailAreaId()), post.getStatus().equals(POST_STATUS_RECRUITING), "");
            postedPostDataList.add(postedPost);
        }
        return new ResponseDto(SuccessMessage.GET_POSTED_POST_LIST_SUCCESS.getCode(),
                SuccessMessage.GET_POSTED_POST_LIST_SUCCESS.getMessage(), new PostedPostResponse(postedPostDataList));
    }

    private String getWorkTypeString(PostSnapshot postSnapshot) {
        return workTypeRepository.findById(postSnapshot.getWorkTypeId())
                .orElseThrow()
                .getType();
    }

    public String getAreaName(Integer areaId) {
        return locationRepository.findById(areaId)
                .orElseThrow()
                .getName();
    }

    public String getWorkTypeName(Integer workTypeId) {
        return workTypeRepository.findById(workTypeId)
                .orElseThrow()
                .getType();
    }
}
