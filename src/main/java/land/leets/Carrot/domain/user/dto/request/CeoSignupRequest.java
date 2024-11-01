package land.leets.Carrot.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
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
    private String ceoName;
    @NotNull
    private String ceoPhoneNumber;
    @NotNull
    private String ceoNumber;
    @NotNull
    private String storeName;
    @NotNull
    private String openDate;
}
