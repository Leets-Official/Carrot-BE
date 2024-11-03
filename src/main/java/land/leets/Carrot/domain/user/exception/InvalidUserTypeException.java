package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.INVALID_USER_TYPE;

import land.leets.Carrot.global.common.exception.BaseException;

public class InvalidUserTypeException extends BaseException {
    public InvalidUserTypeException() {
        super(INVALID_USER_TYPE.getCode(), INVALID_USER_TYPE.getMessage());
    }
}
