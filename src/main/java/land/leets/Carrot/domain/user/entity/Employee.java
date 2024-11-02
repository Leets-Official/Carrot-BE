package land.leets.Carrot.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.Set;
import land.leets.Carrot.domain.apply.entity.Apply;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Employee extends User {
    @Column(length = 20, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String employeeName;

    @Column(nullable = false)
    private String employeeAddress;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Apply> apply;

    public Employee(String email, String password, String phoneNumber, String employeeName, String employeeAddress) {
        super(email, password);
        this.phoneNumber = phoneNumber;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
    }
}
