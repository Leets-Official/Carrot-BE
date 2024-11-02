package land.leets.Carrot.domain.post.exception;

import land.leets.Carrot.global.common.exception.BaseException;

public class PostException extends BaseException {
    public PostException(PostErrorMessage error) {
        super(error.getCode(), error.getErrorMessage());
    }
}
