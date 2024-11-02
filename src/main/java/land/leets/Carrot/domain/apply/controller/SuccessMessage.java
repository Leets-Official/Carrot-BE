package land.leets.Carrot.domain.apply.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    GET_APPLICANT_LIST_SUCCESS(HttpStatus.OK.value(), "지원자 리스트 조회 성공.");

    private final int code;
    private final String message;

}