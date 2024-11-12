package land.leets.Carrot.domain.user.dto.response;

import land.leets.Carrot.domain.user.entity.CareerDetails;

public record CareerResponse(
        String workplace,  // 일한 곳
        String workType,   // 했던 일
        String workYear,   // 일한 연도
        String workPeriod  // 일한 기간
) {
    public static CareerResponse from(CareerDetails careerDetails) {
        return new CareerResponse(
                careerDetails.getWorkplace(),
                careerDetails.getWorkType(),
                careerDetails.getWorkYear(),
                careerDetails.getWorkPeriod()
        );
    }
}
