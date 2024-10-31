package land.leets.Carrot.domain.post.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import land.leets.Carrot.domain.post.dto.request.PostPostRequest;
import land.leets.Carrot.domain.post.dto.response.PostResponse;
import land.leets.Carrot.domain.post.service.PostService;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "PostController", description = "게시글 관련 controller")
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    PostService postService;

    @PostMapping
    public ResponseEntity<Void> postNewPost(@RequestBody @Valid PostPostRequest requestBody) {
        postService.saveNewPost(requestBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResponseDto<PostResponse>> getPost(@RequestParam Long postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }
}
