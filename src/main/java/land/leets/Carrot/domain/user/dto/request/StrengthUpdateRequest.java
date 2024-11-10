package land.leets.Carrot.domain.user.dto.request;

public record StrengthUpdateRequest(
        boolean isDiligent,
        boolean isOnTime,
        boolean isClean,
        boolean isNearHome,
        boolean isSleepless
) {
    public static StrengthUpdateRequest of(boolean isDiligent, boolean isOnTime, boolean isClean,
                                           boolean isNearHome, boolean isSleepless) {
        return new StrengthUpdateRequest(isDiligent, isOnTime, isClean, isNearHome, isSleepless);
    }
}
