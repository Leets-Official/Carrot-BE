package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.CEONAME_ALREADY_EXISTS;

import land.leets.Carrot.global.common.exception.BaseException;

public class CeoNameAlreadyExistsException extends BaseException {
    public CeoNameAlreadyExistsException() {
        super(CEONAME_ALREADY_EXISTS.getCode(), CEONAME_ALREADY_EXISTS.getMessage());
    }
}
