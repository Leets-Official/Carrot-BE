package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.domain.user.entity.Gender;

public record CeoBasicInfoResponse(
        String ceoName,
        String ceoPhoneNumber,
        String ceoAddress,
        Gender gender,
        Integer birthYear
) implements UserBasicInfoResponse {
    public static CeoBasicInfoResponse from(Ceo ceo) {
        return new CeoBasicInfoResponse(
                ceo.getCeoName(),
                ceo.getCeoPhoneNumber(),
                ceo.getCeoAddress(),
                ceo.getGender(),
                ceo.getBirthYear()
        );
    }
}
