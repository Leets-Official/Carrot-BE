package land.leets.Carrot.domain.post.dto.response;

import java.util.List;
import land.leets.Carrot.domain.post.domain.PostedPost;

public record PostedPostResponse(
        List<PostedPost> postedPostList
) {
}
