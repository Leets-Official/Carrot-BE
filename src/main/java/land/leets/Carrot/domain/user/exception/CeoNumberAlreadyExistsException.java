package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.CEONUMBER_ALREADY_EXISTS;

import land.leets.Carrot.global.common.exception.BaseException;

public class CeoNumberAlreadyExistsException extends BaseException {
    public CeoNumberAlreadyExistsException() {
        super(CEONUMBER_ALREADY_EXISTS.getCode(), CEONUMBER_ALREADY_EXISTS.getMessage());
    }
}
