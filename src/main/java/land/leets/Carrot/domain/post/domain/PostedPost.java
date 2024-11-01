package land.leets.Carrot.domain.post.domain;

public record PostedPost(
        Long postId,
        String title,
        String detailAreaName,
        boolean isRecruiting,
        String imageUrl
) {
}
