package land.leets.Carrot.domain.user.service;

import jakarta.servlet.http.HttpServletResponse;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.exception.InvalidPasswordException;
import land.leets.Carrot.domain.user.exception.UserNotFoundException;
import land.leets.Carrot.domain.user.repository.UserRepository;
import land.leets.Carrot.global.auth.jwt.dto.JwtProvider;
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

    public void authenticate(String email, String password, HttpServletResponse response) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException();
        }
        String token = jwtProvider.generateAccessToken(user.getEmail());
        response.setHeader("Authorization", "Bearer " + token);
    }
}
