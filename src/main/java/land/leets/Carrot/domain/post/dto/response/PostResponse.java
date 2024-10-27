package land.leets.Carrot.domain.post.dto.response;

import jakarta.validation.constraints.NotNull;
import land.leets.Carrot.domain.post.entity.PostData;

public record PostResponse(
        Long postId,
        @NotNull
        Long userId,
        @NotNull
        String storeName,
        PostData postData

) {
}
