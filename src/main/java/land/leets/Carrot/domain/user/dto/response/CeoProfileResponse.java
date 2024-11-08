package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Ceo;
import lombok.Getter;

@Getter
public class CeoProfileResponse extends ProfileResponse {
    private final String ceoName;
    private final String ceoPhoneNumber;

    public CeoProfileResponse(Ceo ceo) {
        super(ceo.getGender(), ceo.getBirthYear());
        this.ceoName = ceo.getCeoName();
        this.ceoPhoneNumber = ceo.getCeoPhoneNumber();
    }
}
