package land.leets.Carrot.domain.user.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {


    USER_SAVE_SUCCESS(201, "회원가입 성공."),
    LOGIN_SUCCESS(200, "로그인 성공.");

    private final int code;
    private final String message;

}
