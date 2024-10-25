package land.leets.Carrot.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 20)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int birthYear;

    private String nickname;

    private long uuid;

    private String profileUrl;

    private double temperature;

    private String selfIntroduction;

    @Column(nullable = false)
    private String ceoNumber;
    
    @Column(nullable = false)
    private String ceoName;

    private LocalDate openDate;

    private boolean isSmoke = false;

    private boolean isLongWork = false;

    private boolean isCarLicence = false;

    private boolean isEnglish = false;

    private boolean isMilitary = false;

    private boolean isCookLicence = false;

    public static User createWithEncodedPassword(String email, String encodedPassword,
                                                 String phoneNumber, String name) {
        User user = new User();
        user.email = email;
        user.password = encodedPassword;
        user.phoneNumber = phoneNumber;
        user.nickname = name;
        return user;
    }
}
