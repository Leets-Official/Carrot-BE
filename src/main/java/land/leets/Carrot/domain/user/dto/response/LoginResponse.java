package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.UserType;

public record LoginResponse(
        Long userId,
        UserType userType
) {
}
