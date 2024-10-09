package land.leets.Carrot.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Timestamp;
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

    private String email;

    private String pw;

    private String phoneNumber;

    private Gender gender;

    private int birthYear;

    private String nickname;

    private long uuid;

    private String profile;

    private double temperature;

    private Timestamp lastAccessTime;

    private String selfIntroduction;

    private boolean isSmoke = false;

    private boolean isLongWork = false;

    private boolean isCarLicence = false;

    private boolean isEnglish = false;

    private boolean isMilitary = false;

    private boolean isCookLicence = false;
}
