package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.EMAIL_ALREADY_EXISTS;

import land.leets.Carrot.global.common.exception.BaseException;

public class EmailAlreadyExistsException extends BaseException {
    public EmailAlreadyExistsException() {
        super(EMAIL_ALREADY_EXISTS.getCode(), EMAIL_ALREADY_EXISTS.getMessage());
    }
}
