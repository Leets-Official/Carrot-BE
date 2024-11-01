package land.leets.Carrot.domain.post.domain;

public record ShortPostData(
        String title,
        String storeName,
        String location,
        String payType,
        Long pay,
        String postStatus,
        String imageUrl
) {
}
