package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.USER_ALREADY_EXISTS;

import land.leets.Carrot.global.common.exception.BaseException;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException() {
        super(USER_ALREADY_EXISTS.getCode(), USER_ALREADY_EXISTS.getMessage());
    }
}
