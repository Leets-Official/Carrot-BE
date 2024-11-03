package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.UNKNOWN_USER_TYPE;

import land.leets.Carrot.global.common.exception.BaseException;

public class UnknownUserTypeException extends BaseException {
    public UnknownUserTypeException() {
        super(UNKNOWN_USER_TYPE.getCode(), UNKNOWN_USER_TYPE.getMessage());
    }
}
