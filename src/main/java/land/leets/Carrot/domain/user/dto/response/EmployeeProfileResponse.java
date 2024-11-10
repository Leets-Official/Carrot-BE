package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.Gender;

public record EmployeeProfileResponse(
        String phoneNumber,
        String employeeName,
        String employeeAddress,
        String workplace,
        String workType,
        String workYear,
        String workPeriod,
        String selfIntro,
        boolean isSmoke,
        boolean isLongWork,
        boolean isCarLicense,
        boolean isEnglish,
        boolean isMilitary,
        boolean isCookLicense,
        boolean isDiligent,
        boolean isOnTime,
        boolean isClean,
        boolean isNearHome,
        boolean isSleepless,
        Gender gender,
        Integer birthYear,
        String profileImageUrl
) {
    public static EmployeeProfileResponse from(Employee employee) {
        return new EmployeeProfileResponse(
                employee.getPhoneNumber(),
                employee.getEmployeeName(),
                employee.getEmployeeAddress(),
                employee.getWorkplace(),
                employee.getWorkType(),
                employee.getWorkYear(),
                employee.getWorkPeriod(),
                employee.getSelfIntro(),
                employee.isSmoke(),
                employee.isLongWork(),
                employee.isCarLicense(),
                employee.isEnglish(),
                employee.isMilitary(),
                employee.isCookLicense(),
                employee.isDiligent(),
                employee.isOnTime(),
                employee.isClean(),
                employee.isNearHome(),
                employee.isSleepless(),
                employee.getGender(),
                employee.getBirthYear(),
                employee.getProfileImageUrl()
        );
    }
}
