package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.Gender;

public record EmployeeBasicInfoResponse(
        String employeeName,
        String employeeAddress,
        String phoneNumber,
        String profileImageUrl,
        Gender gender,
        Integer birthYear
) implements UserBasicInfoResponse {
    public static EmployeeBasicInfoResponse from(Employee employee) {
        return new EmployeeBasicInfoResponse(
                employee.getEmployeeName(),
                employee.getEmployeeAddress(),
                employee.getPhoneNumber(),
                employee.getProfileImageUrl(),
                employee.getGender(),
                employee.getBirthYear()
        );
    }
}
