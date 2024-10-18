package land.leets.Carrot.domain.user.service;

import jakarta.servlet.http.HttpServletResponse;
import land.leets.Carrot.domain.user.controller.ResponseMessage;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.exception.ErrorMessage;
import land.leets.Carrot.domain.user.exception.InvalidPasswordException;
import land.leets.Carrot.domain.user.exception.UserNotFoundException;
import land.leets.Carrot.domain.user.repository.UserRepository;
import land.leets.Carrot.global.auth.jwt.dto.JwtProvider;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public ResponseDto<Void> authenticate(String email, String password, HttpServletResponse response) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));

            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new InvalidPasswordException(ErrorMessage.INVALID_PASSWORD);
            }

            String token = jwtProvider.generateAccessToken(user.getEmail());
            response.setHeader("Authorization", "Bearer " + token);

            return ResponseDto.response(
                    ResponseMessage.LOGIN_SUCCESS.getCode(),
                    ResponseMessage.LOGIN_SUCCESS.getMessage()
            );

        } catch (UserNotFoundException | InvalidPasswordException e) {
            return ResponseDto.errorResponse(
                    e instanceof UserNotFoundException
                            ? ErrorMessage.USER_NOT_FOUND.getCode()
                            : ErrorMessage.INVALID_PASSWORD.getCode(),
                    e.getMessage()
            );
        }
    }
}
