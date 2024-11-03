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

    @Column
    private boolean isSmoke;

    @Column
    private boolean isLongWork;

    @Column
    private boolean isCarLicense;

    @Column
    private boolean isEnglish;

    @Column
    private boolean isMilitary;

    @Column
    private boolean isCookLicense;

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

    public void updateAdditionalInfo(boolean isSmoke, boolean isLongWork, boolean isCarLicense,
                                     boolean isEnglish, boolean isMilitary, boolean isCookLicense) {
        this.isSmoke = isSmoke;
        this.isLongWork = isLongWork;
        this.isCarLicense = isCarLicense;
        this.isEnglish = isEnglish;
        this.isMilitary = isMilitary;
        this.isCookLicense = isCookLicense;
    }
}
