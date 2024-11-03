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

    @Column
    private boolean isDiligent = false;

    @Column
    private boolean isOnTime = false;

    @Column
    private boolean isClean = false;

    @Column
    private boolean isNearHome = false;

    @Column
    private boolean isSleepless = false;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<Apply> apply;

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

    public void updateStrengths(boolean diligent, boolean onTime, boolean clean, boolean nearHome, boolean sleepless) {
        this.isDiligent = diligent;
        this.isOnTime = onTime;
        this.isClean = clean;
        this.isNearHome = nearHome;
        this.isSleepless = sleepless;
    }
}
