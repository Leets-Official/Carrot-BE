package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.INVALID_PASSWORD;

import land.leets.Carrot.global.common.exception.BaseException;

public class InvalidPasswordException extends BaseException {

    public InvalidPasswordException() {
        super(INVALID_PASSWORD.getCode(), INVALID_PASSWORD.getMessage());
    }
}
