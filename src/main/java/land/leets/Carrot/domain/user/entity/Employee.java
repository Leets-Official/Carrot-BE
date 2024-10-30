package land.leets.Carrot.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Employee extends User {
    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String nickname;

    public Employee(String email, String password, String phoneNumber, String nickname) {
        super(email, password);
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
    }
}