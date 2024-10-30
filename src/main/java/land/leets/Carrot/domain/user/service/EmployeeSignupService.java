package land.leets.Carrot.domain.user.service;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.user.dto.request.EmployeeSignupRequest;
import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.exception.EmailAlreadyExistsException;
import land.leets.Carrot.domain.user.exception.NicknameAlreadyExistsException;
import land.leets.Carrot.domain.user.exception.TelAlreadyExistsException;
import land.leets.Carrot.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeSignupService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void employeeSignup(EmployeeSignupRequest request) {
        validateEmployee(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Employee employee = new Employee(
                request.getEmail(), encodedPassword, request.getPhoneNumber(), request.getNickname()
        );
        employeeRepository.save(employee);
    }

    private void validateEmployee(EmployeeSignupRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        if (employeeRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new TelAlreadyExistsException();
        }
        if (employeeRepository.existsByNickname(request.getNickname())) {
            throw new NicknameAlreadyExistsException();
        }
    }
}
