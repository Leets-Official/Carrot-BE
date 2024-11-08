package land.leets.Carrot.domain.user.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
@AllArgsConstructor
public enum CeoErrorMessage {
    NOT_CEO_USER(HttpStatus.BAD_GATEWAY.value(), "고용자 유저가 아닙니다.");
    private final Integer code;
    private final String errorMessage;

}
