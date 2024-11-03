package land.leets.Carrot.domain.post.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Set;
import land.leets.Carrot.global.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
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

    private Integer workTypeId;

    private String title;

    private String content;

    private int pay;

    private int workStartHour;

    private int workStartMinute;

    private int workEndHour;
    private int workEndMinute;
    private boolean isNegotiable;

    private String applyNumber;

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

    private String payType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public PostSnapshot(int doAreaId,
                        int siAreaId,
                        int detailAreaId,
                        long postId,
                        Integer workTypeId,
                        String title,
                        String content,
                        int pay,
                        int workStartHour,
                        int workStartMinute,
                        int workEndHour, int workEndMinute, boolean isNegotiable,
                        String applyNumber,
                        boolean isLastest,
                        boolean isNumberPublic,
                        String payType) {
        this.doAreaId = doAreaId;
        this.siAreaId = siAreaId;
        this.detailAreaId = detailAreaId;
        this.postId = postId;
        this.workTypeId = workTypeId;
        this.title = title;
        this.content = content;
        this.pay = pay;
        this.workStartHour = workStartHour;
        this.workStartMinute = workStartMinute;
        this.workEndHour = workEndHour;
        this.workEndMinute = workEndMinute;
        this.isNegotiable = isNegotiable;
        this.applyNumber = applyNumber;
        this.isLastest = isLastest;
        this.isNumberPublic = isNumberPublic;
        this.selectedDays = selectedDays != null ? selectedDays : EnumSet.noneOf(DayOfWeek.class);
        this.payType = payType;
    }

    public void setIsLastest(boolean isLastest){
        this.isLastest = isLastest;
    }

}
