package land.leets.Carrot.global.common.exception;

import land.leets.Carrot.domain.post.exception.ErrorMessage;

public class BaseException extends RuntimeException {

    private final int errorCode;

    public BaseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        // 나중에 InvalidInputException 등 예외처리
    }

    public BaseException(ErrorMessage errorMessage) {
        super(errorMessage.getErrorMessage());
        this.errorCode = errorMessage.getCode();
    }
}
