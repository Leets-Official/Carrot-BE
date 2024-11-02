package land.leets.Carrot.domain.post.mapper;

import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import org.springframework.stereotype.Component;

@Component
public class PostSnapshotMapper {
    public static PostData postSnaphotToPostData(PostSnapshot postSnapshot, String doName, String siName,
                                                 String detailName, String workDays, String workType) {
        return new PostData(doName, siName, detailName, workType, postSnapshot.getTitle(), postSnapshot.getContent(),
                postSnapshot.getPay(), postSnapshot.getWorkStartHour(), postSnapshot.getWorkStartMinute(),
                postSnapshot.getWorkEndHour(), postSnapshot.getWorkEndMinute(), postSnapshot.isNegotiable(),
                postSnapshot.getApplyNumber(), workDays, false, postSnapshot.getCreatedAt(), postSnapshot.getPayType(),
                postSnapshot.isNumberPublic(), null//TODO 이미지 관련 작업 추후 진행 예정
        );
    }
}
