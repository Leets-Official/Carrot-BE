package land.leets.Carrot.domain.user.service;

import java.util.Optional;
import java.util.stream.Stream;
import land.leets.Carrot.domain.user.dto.request.EmployeeSignupRequest;
import land.leets.Carrot.domain.user.dto.response.UserInfoResponse;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.exception.ErrorMessage;
import land.leets.Carrot.domain.user.exception.UserAlreadyExistsException;
import land.leets.Carrot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCreateService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserInfoResponse employeeSignup(EmployeeSignupRequest request) {
        // 이메일과 전화번호 중복 검사
        Stream.of(
                        checkEmailExists(request.getEmail()),
                        checkTelExists(request.getPhoneNumber()),
                        checkNicknameExists(request.getNickname())
                ).filter(Optional::isPresent)
                .findFirst()
                .ifPresent(dup -> {
                    throw dup.get();
                });

        // 비밀번호 암호화 및 유저 생성
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.createWithEncodedPassword(
                request.getEmail(), encodedPassword,
                request.getPhoneNumber(), request.getNickname()
        );

        userRepository.save(user);
        return new UserInfoResponse(user);
    }

    private Optional<RuntimeException> checkEmailExists(String email) {
        return userRepository.existsByEmail(email)
                ? Optional.of(new UserAlreadyExistsException(ErrorMessage.EMAIL_ALREADY_EXISTS))
                : Optional.empty();
    }

    private Optional<RuntimeException> checkTelExists(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber)
                ? Optional.of(new UserAlreadyExistsException(ErrorMessage.TEL_ALREADY_EXISTS))
                : Optional.empty();
    }

    private Optional<RuntimeException> checkNicknameExists(String nickname) {
        return userRepository.existsByNickname(nickname)
                ? Optional.of(new UserAlreadyExistsException(ErrorMessage.NICKNAME_ALREADY_EXISTS))
                : Optional.empty();
    }
}
