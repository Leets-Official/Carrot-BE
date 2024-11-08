package land.leets.Carrot.domain.post.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.entity.DayOfWeek;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import org.springframework.stereotype.Component;

@Component
public class PostSnapshotMapper {
    public static PostData postSnaphotToPostData(PostSnapshot postSnapshot, String doName, String siName,
                                                 String detailName, Set<DayOfWeek> workDays, String workType,
                                                 List<String> imageUrlList) {
        List<String> workDaysList = workDays.stream()
                .map(Enum::name)
                .collect(Collectors.toList());

        return new PostData(doName, siName, detailName, workType, postSnapshot.getTitle(), postSnapshot.getContent(),
                postSnapshot.getPay(), postSnapshot.getWorkStartHour(), postSnapshot.getWorkStartMinute(),
                postSnapshot.getWorkEndHour(), postSnapshot.getWorkEndMinute(), postSnapshot.isNegotiable(),
                postSnapshot.getApplyNumber(), workDaysList, false,
                postSnapshot.getCreatedAt(), postSnapshot.getPayType(),
                postSnapshot.isNumberPublic(), imageUrlList
        );
    }
}
