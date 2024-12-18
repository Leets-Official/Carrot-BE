package land.leets.Carrot.domain.post.mapper;

import java.util.stream.Collectors;
import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.entity.Post;
import land.leets.Carrot.domain.post.entity.DayOfWeek;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import org.springframework.stereotype.Component;

@Component
public class PostDataMapper {
    //단기 알바인 경우는 미반영, 일단 장기 알바만을 고려 isShortTerm 은 아직 고려안함
    public static PostSnapshot postDataToPostSnapshot(PostData postData, Integer doAreaId, Integer siAreaId,
                                                      Integer detailAreaId, Integer jobTypeId, Post post) {
        return PostSnapshot.builder()
                .doAreaId(doAreaId)
                .siAreaId(siAreaId)
                .detailAreaId(detailAreaId)
                .post(post)
                .workTypeId(jobTypeId)
                .title(postData.title())
                .content(postData.content())
                //.postImageUrl("")   //TODO 이미지 관련 기능 차후 구현 예정
                .pay(postData.pay())
                .workStartHour(postData.workStartHour())
                .workStartMinute(postData.workStartMinute())
                .workEndHour(postData.workEndHour())
                .workEndMinute(postData.workEndTimeMinute())
                .isNegotiable(postData.isNegotiable())
                .applyNumber(postData.applyNumber())
                .isLastest(true)
                .isNumberPublic(postData.isNumberPublic())
                .payType(postData.payType())
                .selectedDays(
                        postData.workDays().stream().map(day -> DayOfWeek.valueOf(day)).collect(Collectors.toSet()))
                .build();
    }
}
