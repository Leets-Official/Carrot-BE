package land.leets.Carrot.domain.apply.domain;

public record Applicant(
        Long userId,
        String userNickname,
        String detailAreaName,
        boolean isRecruited
) {
}
