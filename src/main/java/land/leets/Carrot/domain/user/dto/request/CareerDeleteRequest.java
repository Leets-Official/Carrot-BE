package land.leets.Carrot.domain.user.dto.request;

public record CareerDeleteRequest(
        Long careerId
) {
    public static CareerDeleteRequest of(Long careerId) {
        return new CareerDeleteRequest(careerId);
    }
}
