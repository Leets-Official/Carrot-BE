package land.leets.Carrot.domain.apply.dto.response;

import java.util.List;
import land.leets.Carrot.domain.apply.domain.AppliedPost;

public record GetAppliedListResponse(
        List<AppliedPost> postList
) {
}
