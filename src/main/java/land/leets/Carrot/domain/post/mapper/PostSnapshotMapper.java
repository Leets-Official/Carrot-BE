package land.leets.Carrot.domain.post.mapper;

import land.leets.Carrot.domain.post.domain.PostData;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import org.springframework.stereotype.Component;

@Component
public class PostSnapshotMapper {
    public PostData PostSnaphotToPostData(PostSnapshot postSnapshot){
        return PostData
                //TODO postData 빌더 생성
    }
}
