package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.entity.Gender;

public record CeoProfileResponse(
        String ceoName,
        String ceoPhoneNumber,
        String ceoAddress,
        Gender gender,
        Integer birthYear
) implements ProfileResponse {
    public static CeoProfileResponse from(Ceo ceo) {
        return new CeoProfileResponse(
                ceo.getCeoName(),
                ceo.getCeoPhoneNumber(),
                ceo.getCeoAddress(),
                ceo.getGender(),
                ceo.getBirthYear()
        );
    }
}
