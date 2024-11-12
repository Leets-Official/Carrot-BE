package land.leets.Carrot.domain.user.dto.request;

public record CareerAddRequest(
        String workplace, // 일한 곳
        String workType,  // 했던 일
        String workYear,  // 일한 연도
        String workPeriod // 일한 기간
) {
    public static CareerAddRequest of(String workplace, String workType, String workYear, String workPeriod) {
        return new CareerAddRequest(workplace, workType, workYear, workPeriod);
    }
}
