package land.leets.Carrot.domain.post.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.EnumSet;
import java.util.Set;
import land.leets.Carrot.global.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PostSnapshot extends BaseTimeEntity {
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

    private String postImageUrl;

    private int pay;

    private int workStartHour;

    private int workStartMinute;

    private int workEndHour;
    private int workEndMinute;
    private boolean isNegotiable;

    private long phoneNumber;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> selectedDays = EnumSet.noneOf(DayOfWeek.class);

    public void selectDay(DayOfWeek day) {
        selectedDays.add(day);
    }

    public void deselectDay(DayOfWeek day) {
        selectedDays.remove(day);
    }

    public boolean isDaySelected(DayOfWeek day) {
        return selectedDays.contains(day);
    }

    private boolean isLastest = true;

    private boolean isNumberPublic;

}
