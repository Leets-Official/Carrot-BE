package land.leets.Carrot.domain.user.dto.request;

public record AdditionalInfoUpdateRequest(
        boolean isSmoke,
        boolean isLongWork,
        boolean isCarLicense,
        boolean isEnglish,
        boolean isMilitary,
        boolean isCookLicense
) {
    public static AdditionalInfoUpdateRequest of(boolean isSmoke, boolean isLongWork, boolean isCarLicense,
                                                 boolean isEnglish, boolean isMilitary, boolean isCookLicense) {
        return new AdditionalInfoUpdateRequest(isSmoke, isLongWork, isCarLicense, isEnglish, isMilitary, isCookLicense);
    }
}
