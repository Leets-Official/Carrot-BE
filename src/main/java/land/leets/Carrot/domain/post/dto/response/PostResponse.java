package land.leets.Carrot.domain.post.dto.response;

import jakarta.validation.constraints.NotNull;
import land.leets.Carrot.domain.post.domain.PostData;

public record PostResponse(
        Long postId,
        @NotNull
        Long userId,
        @NotNull
        String storeName,
        String writerNickname,
        PostData postData

) {
}
