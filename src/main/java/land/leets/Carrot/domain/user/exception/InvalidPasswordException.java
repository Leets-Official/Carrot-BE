package land.leets.Carrot.domain.user.exception;

import land.leets.Carrot.global.common.exception.BaseException;

public class InvalidPasswordException extends BaseException {

    public InvalidPasswordException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
