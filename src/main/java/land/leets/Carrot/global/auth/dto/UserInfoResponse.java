package land.leets.Carrot.global.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse { // 인증 성공한 사용자 정보 담음
    private String username; // 인증 성공한 사용자 이름
    private String nickname; // 이름으로 구별이 안될 경우 닉네임
}
