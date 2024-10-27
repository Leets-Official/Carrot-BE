package land.leets.Carrot.domain.post.service;

import java.time.LocalDateTime;
import land.leets.Carrot.domain.post.dto.response.PostResponse;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import land.leets.Carrot.domain.post.repository.PostRepository;
import land.leets.Carrot.domain.post.dto.request.PostPostRequest;
import land.leets.Carrot.domain.post.entity.Post;
import land.leets.Carrot.domain.post.entity.PostData;
import land.leets.Carrot.domain.post.repository.PostSnapshotRepository;
import land.leets.Carrot.global.common.exception.BaseException;

public class PostService {
    PostRepository postRepository;
    PostSnapshotRepository postSnapshotRepository;
    public void saveNewPost(PostPostRequest postPostRequest) {
        PostData postData = postPostRequest.postData();
        PostSnapshot postSnapshot = new PostSnapshot();

        Post post = new Post(postPostRequest.userId(), postPostRequest.storeName(), LocalDateTime.now(), "Recruting");
        postRepository.save(post);
        postSnapshotRepository.save(postSnapshot);
    }

}
