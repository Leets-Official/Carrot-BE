package land.leets.Carrot.global.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private String accessToken; // JwtProvider 클래스에서 생성된 jwt 토큰을 담음
}
