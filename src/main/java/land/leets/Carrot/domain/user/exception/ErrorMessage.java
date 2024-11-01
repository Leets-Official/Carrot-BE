package land.leets.Carrot.domain.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ErrorMessage {
    // 회원가입 관련
    EMAIL_ALREADY_EXISTS(400, "이미 존재하는 이메일입니다."),
    TEL_ALREADY_EXISTS(400, "이미 등록된 전화번호입니다."),
    CEONUMBER_ALREADY_EXISTS(400, "이미 존재하는 사업자 번호입니다"),
    CEONAME_ALREADY_EXISTS(400, "이미 존재하는 사업자 이름입니다."),

    // 로그인 관련
    USER_NOT_FOUND(404, "존재하지 않는 유저입니다."),
    INVALID_PASSWORD(401, "비밀번호가 일치하지 않습니다.");

    private final int code;
    private final String message;
}
