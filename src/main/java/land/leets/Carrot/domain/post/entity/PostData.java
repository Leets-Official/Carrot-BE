package land.leets.Carrot.domain.post.entity;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostData {
    //주소 어떻게 념겨주느냐에 따라서 do, si, detail관련 attribute 작업
    private String workType;
    private String title;
    private String content;
    private String 이미지;
    @NotNull
    private Integer pay;
    @NotNull
    private Integer workStartHour;
    @NotNull
    private Integer workStartMinute;
    @NotNull
    private Integer workEndHour;
    @NotNull
    private Integer workEndTimeMinute;
    @NotNull
    private Boolean isNegotiable;
    @NotNull
    private Long phoneNumber;
    @NotNull
    private Boolean isMondaySelected;
    @NotNull
    private Boolean isTuesdaySelected;
    @NotNull
    private Boolean isWednesdaySelected;
    @NotNull
    private Boolean isThursdaySelected;
    @NotNull
    private Boolean isFridaySelected;
    @NotNull
    private Boolean isSaturdaySelected;
    @NotNull
    private Boolean isSundaySelected;
    @NotNull
    private Boolean isShortTermJob;
    private LocalDateTime updatedTime;
    private Boolean isLatest = true;
    @NotNull
    private String payType;

}
