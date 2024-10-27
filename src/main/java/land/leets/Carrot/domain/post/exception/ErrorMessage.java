package land.leets.Carrot.domain.post.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 글을 찾을 수 없습니다.");
    private final Integer code;
    private final String errorMessage;
}
