package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.USER_NOT_FOUND;

import land.leets.Carrot.global.common.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(USER_NOT_FOUND.getCode(), USER_NOT_FOUND.getMessage());
    }
}
