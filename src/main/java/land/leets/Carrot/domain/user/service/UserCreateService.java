package land.leets.Carrot.domain.user.service;

import java.util.Optional;
import java.util.stream.Stream;
import land.leets.Carrot.domain.user.dto.request.CeoSignupRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeSignupRequest;
import land.leets.Carrot.domain.user.dto.response.UserInfoResponse;
import land.leets.Carrot.domain.user.entity.User;
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

    @Transactional
    public UserInfoResponse ceoSignup(CeoSignupRequest request) {
        // 이메일, 사업자번호, 사업자 이름 중복 검사
        Stream.of(
                        checkEmailExists(request.getEmail()),
                        checkCeoNumberExists(request.getCeoNumber()),
                        checkCeoNameExists(request.getCeoName())
                ).filter(Optional::isPresent)
                .findFirst()
                .ifPresent(dup -> {
                    throw dup.get();
                });

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.createWithEncodedPassword(
                request.getEmail(), encodedPassword,
                request.getCeoNumber(), request.getCeoName()
        );

        userRepository.save(user);
        return new UserInfoResponse(user);
    }

    private Optional<RuntimeException> checkEmailExists(String email) {
        return userRepository.existsByEmail(email)
                ? Optional.of(new UserAlreadyExistsException())
                : Optional.empty();
    }

    private Optional<RuntimeException> checkTelExists(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber)
                ? Optional.of(new UserAlreadyExistsException())
                : Optional.empty();
    }

    private Optional<RuntimeException> checkNicknameExists(String nickname) {
        return userRepository.existsByNickname(nickname)
                ? Optional.of(new UserAlreadyExistsException())
                : Optional.empty();
    }

    private Optional<RuntimeException> checkCeoNumberExists(String ceoNumber) {
        return userRepository.existsByCeoNumber(ceoNumber)
                ? Optional.of(new UserAlreadyExistsException())
                : Optional.empty();
    }

    private Optional<RuntimeException> checkCeoNameExists(String ceoName) {
        return userRepository.existsByCeoName(ceoName)
                ? Optional.of(new UserAlreadyExistsException())
                : Optional.empty();
    }

}
