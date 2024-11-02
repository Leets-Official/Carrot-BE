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
    private String storeName;

    @Column(length = 20, nullable = false)
    private String ceoPhoneNumber;

    @Column(nullable = false)
    private String ceoNumber;

    @Column(nullable = false)
    private String openDate;

    @Column(nullable = false)
    private String ceoAddress;

    public Ceo(String email, String password, String ceoName, String ceoPhoneNumber, String ceoNumber, String storeName,
               String openDate, String ceoAddress) {
        super(email, password);
        this.ceoName = ceoName;
        this.ceoPhoneNumber = ceoPhoneNumber;
        this.ceoNumber = ceoNumber;
        this.storeName = storeName;
        this.openDate = openDate;
        this.ceoAddress = ceoAddress;
    }
}
