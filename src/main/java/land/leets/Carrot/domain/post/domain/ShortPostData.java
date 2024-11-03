package land.leets.Carrot.domain.post.domain;

public record ShortPostData(
        Long postId,
        String title,
        String storeName,
        String location,
        String payType,
        Long pay,
        String postStatus,
        String imageUrl
) {
}
