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
    private String employeeName;

    @Column(nullable = false)
    private String employeeAddress;

    @Column
    private String career;

    @Column
    private String selfIntro;

    public Employee(String email, String password, String phoneNumber, String employeeName, String employeeAddress) {
        super(email, password);
        this.phoneNumber = phoneNumber;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
    }

    public void updateEmployeeInfo(String phoneNumber, String employeeName, String employeeAddress) {
        this.phoneNumber = phoneNumber;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
    }

    public void updateCareer(String career) {
        this.career = career;
    }

    public void updateSelfIntro(String selfIntro) {
        this.selfIntro = selfIntro;
    }
}
