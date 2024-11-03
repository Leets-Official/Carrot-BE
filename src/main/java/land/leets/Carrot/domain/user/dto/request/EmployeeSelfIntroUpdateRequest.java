package land.leets.Carrot.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmployeeSelfIntroUpdateRequest {
    @NotNull
    private String selfIntro;
}
