package land.leets.Carrot.domain.post.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PostSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int doAreaId;

    private int siAreaId;

    private int detailAreaId;

    private long postId;

    private int joyTypeId;

    private String title;

    private String content;

    private String image;

    private int pay;

    private int workStartHour;

    private int workStartMinute;

    private int workEndHour;
    private int workEndMinute;
    private boolean isNegotiable;

    private long phoneNumber;

    private boolean isMondaySelected;

    private boolean isTuesdaySelected;

    private boolean isWednesdaySelected;

    private boolean isThursdaySelected;

    private boolean isFridaySelected;

    private boolean isSaturdaySelected;

    private boolean isSundaySelected;

    private boolean isShortTimeJob;

    private Timestamp updatedTime;

    private boolean isLastest = true;

    private boolean isNumberPublic;

}
