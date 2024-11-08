package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Gender;
import lombok.Getter;

@Getter
public abstract class ProfileResponse {
    private final Gender gender;
    private final Integer birthYear;

    protected ProfileResponse(Gender gender, Integer birthYear) {
        this.gender = gender;
        this.birthYear = birthYear;
    }
}
