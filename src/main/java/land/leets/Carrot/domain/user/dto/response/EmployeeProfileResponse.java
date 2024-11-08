package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Employee;
import lombok.Getter;

@Getter
public class EmployeeProfileResponse extends ProfileResponse {
    private final String employeeName;
    private final String employeeAddress;
    private final String phoneNumber;

    public EmployeeProfileResponse(Employee employee) {
        super(employee.getGender(), employee.getBirthYear());
        this.employeeName = employee.getEmployeeName();
        this.employeeAddress = employee.getEmployeeAddress();
        this.phoneNumber = employee.getPhoneNumber();
    }
}
