package land.leets.Carrot.domain.post.mapper;

import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import org.springframework.stereotype.Component;

@Component
public class PostDataMapper {
    public PostSnapshot postDataToPostSnapshot(PostData postData, Integer doAreaId, Integer siAreaId, Integer detailAreaId){
        return PostSnapshot.builder
                .//TODO postSnapshot 빌더 생성
    }
}
