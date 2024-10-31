package land.leets.Carrot.domain.user.exception;

import static land.leets.Carrot.domain.user.exception.ErrorMessage.NICKNAME_ALREADY_EXISTS;

import land.leets.Carrot.global.common.exception.BaseException;

public class NicknameAlreadyExistsException extends BaseException {
    public NicknameAlreadyExistsException() {
        super(NICKNAME_ALREADY_EXISTS.getCode(), NICKNAME_ALREADY_EXISTS.getMessage());
    }
}
