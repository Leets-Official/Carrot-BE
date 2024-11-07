package land.leets.Carrot.domain.post.dto.request;

import jakarta.validation.constraints.NotNull;
import land.leets.Carrot.domain.post.domain.PostData;

public record PostPostRequest(
        Long postId,
        Long userId,
        String storeName,
        String workPlaceAddress,
        PostData postData

) {
}

