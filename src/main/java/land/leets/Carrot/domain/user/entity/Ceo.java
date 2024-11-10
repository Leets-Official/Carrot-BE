package land.leets.Carrot.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.Set;
import land.leets.Carrot.domain.post.entity.Post;
import land.leets.Carrot.domain.user.dto.request.CeoSignupRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor
@Entity
public class Ceo extends User {

    @Column(nullable = false)
    private String ceoName;

    @Column(nullable = false)
    private String storeName;

    @Column(length = 20, nullable = false)
    private String ceoPhoneNumber;

    @Column(nullable = false)
    private String ceoNumber;

    @Column(nullable = false)
    private String openDate;

    @Column(nullable = false)
    private String ceoAddress;

    @OneToMany(mappedBy = "ceo", fetch = FetchType.LAZY)
    private Set<Post> post;

    public Ceo(String email, String password, String ceoName, String ceoPhoneNumber, String ceoNumber, String storeName,
               String openDate, String ceoAddress) {
        super(email, password);
        this.ceoName = ceoName;
        this.ceoPhoneNumber = ceoPhoneNumber;
        this.ceoNumber = ceoNumber;
        this.storeName = storeName;
        this.openDate = openDate;
        this.ceoAddress = ceoAddress;
        this.userType = UserType.CEO;
    }

    public static Ceo fromSignupRequest(CeoSignupRequest request, PasswordEncoder encoder) {
        return new Ceo(
                request.email(),
                encoder.encode(request.password()),
                request.ceoName(),
                request.ceoPhoneNumber(),
                request.ceoNumber(),
                request.storeName(),
                request.openDate(),
                request.ceoAddress()
        );
    }

    public void updateCeoInfo(String ceoPhoneNumber, String ceoName, String ceoAddress) {
        this.ceoPhoneNumber = ceoPhoneNumber;
        this.ceoName = ceoName;
        this.ceoAddress = ceoAddress;
    }
}
