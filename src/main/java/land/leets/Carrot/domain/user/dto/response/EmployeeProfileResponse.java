package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.Gender;
import lombok.Getter;

@Getter
public class EmployeeProfileResponse {
    private final String employeeName;
    private final String employeeAddress;
    private final String phoneNumber;
    private final Gender gender;
    private final Integer birthYear;

    public EmployeeProfileResponse(Employee employee) {
        this.employeeName = employee.getEmployeeName();
        this.employeeAddress = employee.getEmployeeAddress();
        this.phoneNumber = employee.getPhoneNumber();
        this.gender = employee.getGender();
        this.birthYear = employee.getBirthYear();
    }
}
