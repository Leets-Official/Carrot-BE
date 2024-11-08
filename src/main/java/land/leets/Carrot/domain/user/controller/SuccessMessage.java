package land.leets.Carrot.domain.user.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {
    GET_CEO_INFO_SUCCESS(HttpStatus.OK.value(), "사업자 정보 조회 성공");

    private final int code;
    private final String message;

}
