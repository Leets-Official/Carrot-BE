package land.leets.Carrot.global.common.exception;

public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
        // 나중에 InvalidInputException 등 예외처리
    }
}
