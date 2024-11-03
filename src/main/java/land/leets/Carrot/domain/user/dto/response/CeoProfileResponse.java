package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.entity.Gender;
import lombok.Getter;

@Getter
public class CeoProfileResponse {
    private final String ceoName;
    private final String ceoPhoneNumber;
    private final Gender gender;
    private final Integer birthYear;

    public CeoProfileResponse(Ceo ceo) {
        this.ceoName = ceo.getCeoName();
        this.ceoPhoneNumber = ceo.getCeoPhoneNumber();
        this.gender = ceo.getGender();
        this.birthYear = ceo.getBirthYear();
    }
}
