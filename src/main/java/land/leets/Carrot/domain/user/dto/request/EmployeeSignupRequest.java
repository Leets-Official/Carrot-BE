package land.leets.Carrot.domain.user.dto.request;

public record EmployeeSignupRequest(
        String email,
        String password,
        String phoneNumber,
        String employeeName,
        String employeeAddress
) {
}
