package land.leets.Carrot.domain.user.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}