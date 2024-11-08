package land.leets.Carrot.domain.apply.domain;

public record AppliedPost(
        Long postId,
        String title,
        String storeName,
        String imageUrl,
        Boolean isAccepted, // 조회 유저가 합격했는지
        Boolean isApplicationClosed //공고가 마감되었는지
) {
}
