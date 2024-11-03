package land.leets.Carrot.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmployeeAdditionalInfoUpdateRequest {
    @NotNull
    private boolean isSmoke;

    @NotNull
    private boolean isLongWork;

    @NotNull
    private boolean isCarLicense;

    @NotNull
    private boolean isEnglish;

    @NotNull
    private boolean isMilitary;

    @NotNull
    private boolean isCookLicense;

}
