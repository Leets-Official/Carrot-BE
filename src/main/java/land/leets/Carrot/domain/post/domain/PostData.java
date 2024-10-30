package land.leets.Carrot.domain.post.domain;

import java.time.LocalDateTime;
import lombok.Getter;

public record PostData(
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
        Long applyNumber,
        String workDays, //","로 구분해서 String 으로 받는 가정, 단기알바인 경우
        Boolean isShortTermJob,
        LocalDateTime updatedTime,
        String payType,
        Boolean isNumberPublic) {

}
