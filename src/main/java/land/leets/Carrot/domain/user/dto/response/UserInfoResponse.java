package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.User;
import lombok.Getter;

@Getter
public class UserInfoResponse {
    private final String email;
    private final String phoneNumber;
    private final String nickname;

    public UserInfoResponse(User user) {
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.nickname = user.getNickname();
    }
}
