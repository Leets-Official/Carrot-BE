package land.leets.Carrot.domain.user.dto.request;

public record SelfIntroUpdateRequest(
        String selfIntro
) {
    public static SelfIntroUpdateRequest of(String selfIntro) {
        return new SelfIntroUpdateRequest(selfIntro);
    }
}
