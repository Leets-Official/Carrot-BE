package land.leets.Carrot.domain.post.dto.request;

import jakarta.validation.constraints.NotNull;
import land.leets.Carrot.domain.post.domain.PostData;

public record PostPostRequest(
        Long postId,
        @NotNull
        Long userId,
        @NotNull
        String storeName,
        @NotNull
        String workPlaceAddress,
        PostData postData

) {
}

