package land.leets.Carrot.domain.user.service;

import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.exception.ErrorMessage;
import land.leets.Carrot.domain.user.exception.InvalidPasswordException;
import land.leets.Carrot.domain.user.exception.UserNotFoundException;
import land.leets.Carrot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(ErrorMessage.USER_NOT_FOUND));

        if (!passwordEncoder.matches(password, user.getPw())) {
            throw new InvalidPasswordException(ErrorMessage.INVALID_PASSWORD);
        }
        return user;
    }
}
