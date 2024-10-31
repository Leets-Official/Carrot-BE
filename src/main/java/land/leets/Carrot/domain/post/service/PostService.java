package land.leets.Carrot.domain.post.service;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import land.leets.Carrot.domain.career.entity.WorkType;
import land.leets.Carrot.domain.career.repository.WorkTypeRepository;
import land.leets.Carrot.domain.location.entity.DetailArea;
import land.leets.Carrot.domain.location.repository.LocationRepository;
import land.leets.Carrot.domain.post.controller.SuccessMessage;
import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.dto.request.PostDeleteRequest;
import land.leets.Carrot.domain.post.dto.request.PostPostRequest;
import land.leets.Carrot.domain.post.dto.response.PostResponse;
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
        PostData postData = postPostRequest.postData();
        Integer doAreaId = getAreaId(postData.doName());
        Integer siAreaId = getAreaId(postData.siName());
        Integer detailAreaId = getAreaId(postData.detailName());

        Integer jobTypeId = Math.toIntExact(workTypeRepository.findByType(postData.workType())
                .orElse(workTypeRepository.save(new WorkType(postData.workType())))
                .getId());

        PostSnapshot postSnapshot = PostDataMapper.postDataToPostSnapshot(postData, doAreaId, siAreaId, detailAreaId,
                jobTypeId, postPostRequest.postId());

        Post post = new Post(postPostRequest.userId(), postPostRequest.storeName(), LocalDateTime.now(), POST_STATUS_RECRUITING);
        postRepository.save(post);
        postSnapshotRepository.save(postSnapshot);
    }

    public Integer getAreaId(String areaName) {
        return locationRepository.findByName(areaName)
                .orElse(locationRepository.save(new DetailArea(areaName)))
                .getAreaId();
    }

    public ResponseDto<PostResponse> getPost(Long postId) {
        //존재 여부 조회
        Post post = postRepository.findById(postId).orElseThrow(() -> new BaseException(ErrorMessage.POST_NOT_FOUND.getCode(), ErrorMessage.POST_NOT_FOUND.getErrorMessage()));
        //스냅샷 db에서 검색
        PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndLastestTrue(postId)
                .orElseThrow();

        String workType = getWorkTypeString(postSnapshot);
        PostResponse postResponse = new PostResponse(postId, post.getUserId(), post.getStoreName(),
                PostSnapshotMapper.postSnaphotToPostData(postSnapshot, getAreaName(postSnapshot.getDoAreaId())
                        , getAreaName(postSnapshot.getSiAreaId()), getAreaName(postSnapshot.getDetailAreaId()),
                        getWorkTypeName(postSnapshot.getWorkTypeId()), workType));

        return new ResponseDto(SuccessMessage.GET_POST_DETAIL_SUCCESS.getCode(), SuccessMessage.GET_POST_DETAIL_SUCCESS.getMessage(), postResponse);
    }

    @Transactional
    public void updatePostStatusDelete(PostDeleteRequest postDeleteRequest){
        Post post = postRepository.findById(postDeleteRequest.postId())
                .orElseThrow();
        post.setStatus(POST_STATUS_DELETED);
        postRepository.save(post);
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
