package land.leets.Carrot.domain.apply.exception;

import land.leets.Carrot.global.common.exception.BaseException;

public class ApplyException extends BaseException {
    public ApplyException(ApplyErrorMessage error) {
        super(error.getCode(), error.getErrorMessage());
    }
}
