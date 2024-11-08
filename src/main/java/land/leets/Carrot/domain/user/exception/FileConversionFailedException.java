package land.leets.Carrot.domain.user.exception;

import land.leets.Carrot.global.common.exception.BaseException;

public class FileConversionFailedException extends BaseException {
    public FileConversionFailedException() {
        super(ErrorMessage.FILE_CONVERSION_FAILED.getCode(), ErrorMessage.FILE_CONVERSION_FAILED.getMessage());
    }
}
