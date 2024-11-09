package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private Long userId;
    private UserType userType;
}
