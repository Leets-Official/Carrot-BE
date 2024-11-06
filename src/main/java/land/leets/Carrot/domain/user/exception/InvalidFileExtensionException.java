package land.leets.Carrot.domain.user.exception;

import land.leets.Carrot.global.common.exception.BaseException;

public class InvalidFileExtensionException extends BaseException {
    public InvalidFileExtensionException() {
        super(ErrorMessage.INVALID_FILE_EXTENSION.getCode(), ErrorMessage.INVALID_FILE_EXTENSION.getMessage());
    }
}
