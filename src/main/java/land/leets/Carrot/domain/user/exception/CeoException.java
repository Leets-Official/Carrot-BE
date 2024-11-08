package land.leets.Carrot.domain.user.exception;

import land.leets.Carrot.domain.user.controller.CeoErrorMessage;
import land.leets.Carrot.global.common.exception.BaseException;

public class CeoException extends BaseException {
    public CeoException(CeoErrorMessage error) {
        super(error.getCode(), error.getErrorMessage());
    }
}
