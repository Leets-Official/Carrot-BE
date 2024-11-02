package land.leets.Carrot.domain.apply.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApplyErrorMessage {
    APPLY_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 유저의 이 게시글의 지원 내역이 없습니다."),
    POST_NOT_RECRUITING(HttpStatus.NOT_FOUND.value(), "해당 게시글은 구인 중이 아닙니다.");
    private final Integer code;
    private final String errorMessage;
}
