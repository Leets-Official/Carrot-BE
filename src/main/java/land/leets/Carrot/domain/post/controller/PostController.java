package land.leets.Carrot.domain.post.controller;

import static land.leets.Carrot.domain.user.controller.ResponseMessage.IMAGE_UPLOAD_SUCCESS;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import land.leets.Carrot.domain.apply.dto.response.GetAppliedListResponse;
import land.leets.Carrot.domain.career.service.WorkTypeService;
import land.leets.Carrot.domain.image.service.S3ImageService;
import land.leets.Carrot.domain.post.dto.request.GetPostedPostRequest;
import land.leets.Carrot.domain.post.dto.request.PostPostImageRequest;
import land.leets.Carrot.domain.post.dto.request.PostPostRequest;
import land.leets.Carrot.domain.post.dto.response.PostAImageUrlResponse;
import land.leets.Carrot.domain.post.dto.response.PostImageUrlResponse;
import land.leets.Carrot.domain.post.dto.response.PostResponse;
import land.leets.Carrot.domain.post.dto.response.PostedPostResponse;
import land.leets.Carrot.domain.post.dto.response.ShortPostResponse;
import land.leets.Carrot.domain.post.dto.response.WorkTypeResponse;
import land.leets.Carrot.domain.post.service.PostService;
import land.leets.Carrot.global.auth.annotation.CurrentUser;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Tag(name = "PostController", description = "게시글 관련 controller")
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;
    private final WorkTypeService workTypeService;
    private final S3ImageService s3ImageService;

    @PostMapping
    public ResponseEntity<Void> postNewPost(@RequestBody @Valid PostPostRequest requestBody) {
        postService.saveNewPost(requestBody);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody @Valid PostPostRequest requestBody) {
        postService.updatePost(postId, requestBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ResponseDto<PostResponse>> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getDetailPost(postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.updatePostStatusDelete(postId);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<ResponseDto<ShortPostResponse>> getShortPostData() {
        return ResponseEntity.ok(postService.getShortPostData());
    }

    @GetMapping("/user/posted")
    public ResponseEntity<ResponseDto<PostedPostResponse>> getPostedPostList(
            @RequestBody @Valid GetPostedPostRequest requestBody) {
        return ResponseEntity.ok(postService.getPostedPostList(requestBody));
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<ResponseDto<ShortPostResponse>> getPostsByKeywordSearch(@PathVariable String keyword) {
        return ResponseEntity.ok(postService.getPostByKeywordSearch(keyword));
    }

    @GetMapping("/work/type")
    public ResponseEntity<ResponseDto<WorkTypeResponse>> getCurrentExistWorkType() {
        return ResponseEntity.ok(workTypeService.getWorkType());
    }

    @PatchMapping("/status/{postId}")
    public ResponseEntity<Void> getPostStatusDone(@PathVariable Long postId) {
        postService.updatePostStatusDone(postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/images", consumes = "multipart/form-data")
    public ResponseEntity<PostImageUrlResponse> postPostImages(@RequestPart PostPostImageRequest requestBody){
        return ResponseEntity.ok(postService.getImageUrlList(requestBody));
    }

    //게시글 복수 이미지 업로드 API미작동시  이미지 업로드 단일 API 대용 구현
    @PostMapping("/upload-post-image")
    @Operation(summary = "게시글 단일 이미지 업로드")
    public ResponseEntity<ResponseDto<PostAImageUrlResponse>> uploadProfileImage(
            @RequestParam("image") MultipartFile image,
            @Parameter(hidden = true) @CurrentUser Long userId) {
        String imageUrl = s3ImageService.uploadImage(image, "post-images");

        return ResponseEntity.ok(
                ResponseDto.response(IMAGE_UPLOAD_SUCCESS.getCode(),
                        IMAGE_UPLOAD_SUCCESS.getMessage(), new PostAImageUrlResponse(imageUrl))
        );
    }

    @GetMapping("/post/applied/{userId}")
    public ResponseEntity<ResponseDto<GetAppliedListResponse>> getAppliedList(
            @PathVariable Long userId){
        return ResponseEntity.ok(postService.getAppliedList(userId));
    }
}
