package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.Gender;

public record EmployeeProfileResponse(
        String employeeName,
        String employeeAddress,
        String phoneNumber,
        Gender gender,
        Integer birthYear
) implements ProfileResponse {
    public static EmployeeProfileResponse from(Employee employee) {
        return new EmployeeProfileResponse(
                employee.getEmployeeName(),
                employee.getEmployeeAddress(),
                employee.getPhoneNumber(),
                employee.getGender(),
                employee.getBirthYear()
        );
    }
}
