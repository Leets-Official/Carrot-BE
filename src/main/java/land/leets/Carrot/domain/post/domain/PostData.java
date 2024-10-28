package land.leets.Carrot.domain.post.domain;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public record PostData(
        //주소 어떻게 념겨주느냐에 따라서 do, si, detail관련 attribute 작업
        String doName,
        String siName,
        String detailName,
        String workType,
        String title,
        String content,
        String imageUrl,
        Integer pay,
        Integer workStartHour,
        Integer workStartMinute,
        Integer workEndHour,
        Integer workEndTimeMinute,
        Boolean isNegotiable,
        Long phoneNumber,
        String workDays, //","로 구분해서 String 으로 받는 가정
        Boolean isShortTermJob,
        LocalDateTime updatedTime,
        String payType) {

}
