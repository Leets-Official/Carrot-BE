package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.TEL_ALREADY_EXISTS;

import land.leets.Carrot.global.common.exception.BaseException;

public class TelAlreadyExistsException extends BaseException {
    public TelAlreadyExistsException() {
        super(TEL_ALREADY_EXISTS.getCode(), TEL_ALREADY_EXISTS.getMessage());
    }
}
