package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.entity.Employee;
import land.leets.Carrot.domain.user.entity.Gender;
import land.leets.Carrot.domain.user.entity.User;
import land.leets.Carrot.domain.user.exception.UnknownUserTypeException;

public sealed interface ProfileResponse permits CeoProfileResponse, EmployeeProfileResponse {
    Gender gender();

    Integer birthYear();

    static ProfileResponse from(User user) {
        if (user instanceof Employee employee) {
            return EmployeeProfileResponse.from(employee);
        } else if (user instanceof Ceo ceo) {
            return CeoProfileResponse.from(ceo);
        } else {
            throw new UnknownUserTypeException();
        }
    }
}
