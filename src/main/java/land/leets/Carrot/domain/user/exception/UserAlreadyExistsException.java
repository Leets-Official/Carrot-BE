package land.leets.Carrot.domain.user.exception;

import land.leets.Carrot.global.common.exception.BaseException;

public class UserAlreadyExistsException extends BaseException {
    public UserAlreadyExistsException(ErrorMessage errorMessage) {
        super(errorMessage.getCode(), errorMessage.getMessage());
    }
}
