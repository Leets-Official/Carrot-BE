package land.leets.Carrot.global.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

    INVALID_TOKEN(401, "유효하지 않은 토큰입니다."), // 유효하지 않은 토큰 -> Http 상태 코드 401 반환
    UNAUTHORIZED(401, "인증정보가 존재하지 않습니다."); // 인증되지 않은 토큰 -> Http 상태 코드 401 반환

    private final int code;
    private final String message;
}
