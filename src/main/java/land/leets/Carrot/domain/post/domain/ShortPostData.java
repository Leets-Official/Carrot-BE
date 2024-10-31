package land.leets.Carrot.domain.post.domain;

public record ShortPostData(
        String title,
        String storeName,
        String location,
        String payType,
        int pay,
        String postStatus,
        String imageUrl
) {
}
