package land.leets.Carrot.domain.post.dto.response;

import java.util.List;
import land.leets.Carrot.domain.post.domain.ShortPostData;

public record ShortPostResponse(
        List<ShortPostData> shortPostDataList
) {
}
