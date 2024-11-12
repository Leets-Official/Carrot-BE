package land.leets.Carrot.domain.user.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.Gender;

public record EmployeeProfileResponse(
        String phoneNumber,
        String employeeName,
        String employeeAddress,
        List<CareerResponse> careers,
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
                employee.getCareerDetails().stream()
                        .map(CareerResponse::from)
                        .collect(Collectors.toList()),
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
