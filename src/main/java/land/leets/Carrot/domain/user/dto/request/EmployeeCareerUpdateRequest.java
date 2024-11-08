package land.leets.Carrot.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmployeeCareerUpdateRequest {
    @NotBlank
    private String workplace; // 일한 곳
    @NotBlank
    private String workType;  // 했던 일
    @NotBlank
    private String workYear;  // 일한 연도
    @NotBlank
    private String workPeriod; // 일한 기간
}
