package land.leets.Carrot.global.common.exception;

public class BaseException extends RuntimeException {

    private final int errorCode;

    public BaseException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        // 나중에 InvalidInputException 등 예외처리
    }
}
