package land.leets.Carrot.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmployeeStrengthUpdateRequest {
    @NotNull
    private boolean isDiligent;
    @NotNull
    private boolean isOnTime;
    @NotNull
    private boolean isClean;
    @NotNull
    private boolean isNearHome;
    @NotNull
    private boolean isSleepless;
}
