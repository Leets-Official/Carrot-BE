package land.leets.Carrot.domain.user.service;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.user.dto.request.EmployeeSignupRequest;
import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.exception.EmailAlreadyExistsException;
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
    private final UserService userService;

    @Transactional
    public void employeeSignup(EmployeeSignupRequest request) {
        validateEmployee(request);
        String encodedPassword = passwordEncoder.encode(request.password());
        Employee employee = new Employee(
                request.email(), encodedPassword, request.phoneNumber(),
                request.employeeName(), request.employeeAddress()
        );
        employeeRepository.save(employee);
    }

    private void validateEmployee(EmployeeSignupRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException();
        }
        if (employeeRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new TelAlreadyExistsException();
        }
    }

    public void checkEmailDuplicate(String email) {
        userService.checkEmailDuplicate(email);
    }
}