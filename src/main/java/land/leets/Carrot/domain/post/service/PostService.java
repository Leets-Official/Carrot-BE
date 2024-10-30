package land.leets.Carrot.domain.post.service;

import java.time.LocalDateTime;
import land.leets.Carrot.domain.career.entity.WorkType;
import land.leets.Carrot.domain.career.repository.WorkTypeRepository;
import land.leets.Carrot.domain.location.entity.DetailArea;
import land.leets.Carrot.domain.location.repository.LocationRepository;
import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.dto.request.PostPostRequest;
import land.leets.Carrot.domain.post.dto.response.PostResponse;
import land.leets.Carrot.domain.post.entity.Post;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import land.leets.Carrot.domain.post.mapper.PostDataMapper;
import land.leets.Carrot.domain.post.mapper.PostSnapshotMapper;
import land.leets.Carrot.domain.post.repository.PostRepository;
import land.leets.Carrot.domain.post.repository.PostSnapshotRepository;
import land.leets.Carrot.global.common.exception.BaseException;

public class PostService {
    PostRepository postRepository;
    PostSnapshotRepository postSnapshotRepository;
    LocationRepository locationRepository;

    WorkTypeRepository workTypeRepository;

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

        Post post = new Post(postPostRequest.userId(), postPostRequest.storeName(), LocalDateTime.now(), "Recruting");
        postRepository.save(post);
        postSnapshotRepository.save(postSnapshot);
    }

    public Integer getAreaId(String areaName) {
        return locationRepository.findByName(areaName)
                .orElse(locationRepository.save(new DetailArea(areaName)))
                .getAreaId();
    }

    public PostResponse getPost(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new BaseException()); //TODO Exception 어떻게 던질지 고민
        PostSnapshot postSnapshot = postSnapshotRepository.findByPostIdAndLastestTrue(postId);

        String workType = getWorkTypeString(postSnapshot);
        PostResponse postResponse = new PostResponse(postId, post.getUserId(), post.getStoreName(),
                PostSnapshotMapper.postSnaphotToPostData(postSnapshot, getAreaName(postSnapshot.getDoAreaId())
                        , getAreaName(postSnapshot.getSiAreaId()), getAreaName(postSnapshot.getDetailAreaId()),
                        getWorkTypeName(postSnapshot.getWorkTypeId()), workType));

        return postResponse;
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
