package land.leets.Carrot.domain.apply.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApplyErrorMessage {
    ALREADY_APPLIED(HttpStatus.BAD_REQUEST.value(), "이미 지원한 게시글입니다"),

    APPLY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 유저의 이 게시글의 지원 내역이 없습니다."),
    POST_NOT_RECRUITING(HttpStatus.NOT_FOUND.value(), "해당 게시글은 구인 중이 아닙니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 게시글을 찾을 수 없습니다."),
    EMPLOYEE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 지원자 유저가 존재하지 않습니다.");
    private final Integer code;
    private final String errorMessage;
}
