package land.leets.Carrot.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CeoSignupRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String ceoNumber;
    @NotNull
    private String ceoName;
    @NotNull
    private LocalDate openDate;
}