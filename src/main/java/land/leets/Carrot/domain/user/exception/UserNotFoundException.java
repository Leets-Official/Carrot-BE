package land.leets.Carrot.domain.user.exception;

import land.leets.Carrot.global.common.exception.BaseException;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.getCode(), errorMessage.getMessage());
    }
}
