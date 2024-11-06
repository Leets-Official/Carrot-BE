package land.leets.Carrot.domain.post.domain;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record PostData(
        String doName,
        String siName,
        String detailName,
        String workType,
        String title,
        String content,
        Integer pay,
        Integer workStartHour,
        Integer workStartMinute,
        Integer workEndHour,
        Integer workEndTimeMinute,
        Boolean isNegotiable,
        String applyNumber,
        List<String> workDays, //근무 요일
        Boolean isShortTermJob,
        LocalDateTime lastUpdatedTime,
        String payType,
        Boolean isNumberPublic,
        List<MultipartFile> imageList,
        List<String> imageUrlList) {

}
