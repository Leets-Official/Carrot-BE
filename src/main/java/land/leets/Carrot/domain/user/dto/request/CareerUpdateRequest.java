package land.leets.Carrot.domain.user.dto.request;

public record CareerUpdateRequest(

        String workplace, // 일한 곳

        String workType,  // 했던 일

        String workYear,  // 일한 연도

        String workPeriod // 일한 기간
) {
    public static CareerUpdateRequest of(String workplace, String workType, String workYear, String workPeriod) {
        return new CareerUpdateRequest(workplace, workType, workYear, workPeriod);
    }
}
