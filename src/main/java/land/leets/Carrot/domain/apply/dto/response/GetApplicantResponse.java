package land.leets.Carrot.domain.apply.dto.response;

import java.util.List;
import land.leets.Carrot.domain.apply.domain.Applicant;

public record GetApplicantResponse(
        List<Applicant> applicantList
) {
}
