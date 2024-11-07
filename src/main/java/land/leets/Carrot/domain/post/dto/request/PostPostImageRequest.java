package land.leets.Carrot.domain.post.dto.request;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record PostPostImageRequest(
        List<MultipartFile> imageList
) {
}
