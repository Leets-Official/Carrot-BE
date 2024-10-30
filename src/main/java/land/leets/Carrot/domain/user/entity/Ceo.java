package land.leets.Carrot.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Ceo extends User {
    @Column(nullable = false)
    private String ceoName;

    @Column(nullable = false)
    private String ceoNumber;

    @Column(nullable = false)
    private String openDate;

    public Ceo(String email, String password, String ceoNumber, String ceoName, String openDate) {
        super(email, password);
        this.ceoNumber = ceoNumber;
        this.ceoName = ceoName;
        this.openDate = openDate;
    }
}
