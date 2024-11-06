package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.IMAGE_DELETE_FAILED;

import land.leets.Carrot.global.common.exception.BaseException;

public class ImageDeleteException extends BaseException {
    public ImageDeleteException() {
        super(IMAGE_DELETE_FAILED.getCode(), IMAGE_DELETE_FAILED.getMessage());
    }
}
