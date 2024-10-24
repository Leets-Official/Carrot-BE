package land.leets.Carrot.domain.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupRequest {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String nickname;
}
