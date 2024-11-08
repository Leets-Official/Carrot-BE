package land.leets.Carrot.domain.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorMessage {
    // 회원가입 관련
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    TEL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "이미 등록된 전화번호입니다."),
    CEONUMBER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 사업자 번호입니다"),
    CEONAME_ALREADY_EXISTS(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 대표자명입니다."),

    // 로그인 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "존재하지 않는 유저입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED.value(), "비밀번호가 일치하지 않습니다."),

    // 유저 프로필 관련
    UNKNOWN_USER_TYPE(HttpStatus.BAD_REQUEST.value(), "정의되지 않은 유저 타입입니다."),
    INVALID_USER_TYPE(HttpStatus.BAD_REQUEST.value(), "구직자만 수정 가능한 정보입니다."),

    // 이미지 업로드 관련
    INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST.value(), "유효하지 않은 파일 형식입니다."),
    FILE_CONVERSION_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "파일 변환에 실패했습니다."),
    IMAGE_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR.value(), "이미지 삭제에 실패했습니다.");


    private final int code;
    private final String message;
}
