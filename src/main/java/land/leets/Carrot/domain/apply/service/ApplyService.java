package land.leets.Carrot.domain.apply.service;

import static land.leets.Carrot.domain.apply.exception.ApplyErrorMessage.APPLY_NOT_FOUND;
import static land.leets.Carrot.domain.apply.exception.ApplyErrorMessage.POST_NOT_RECRUITING;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.apply.dto.request.ApplyRequest;
import land.leets.Carrot.domain.apply.entity.Apply;
import land.leets.Carrot.domain.apply.exception.ApplyException;
import land.leets.Carrot.domain.apply.repository.ApplyRepository;
import land.leets.Carrot.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final PostRepository postRepository;

    private static final String POST_STATUS_RECRUITING = "recruiting";

    public void postApply(ApplyRequest applyRequest) {
        Apply apply = new Apply(applyRequest.postId(), applyRequest.userId());
        if (postRepository.findById(applyRequest.postId()).equals(POST_STATUS_RECRUITING)) {
            applyRepository.save(apply);
        } else {
            throw new ApplyException(POST_NOT_RECRUITING);
        }
    }

    public void setStatusRecruited(ApplyRequest applyRequest) {
        Apply apply = applyRepository.findByPostIdAndUserID(applyRequest.postId(), applyRequest.userId())
                .orElseThrow(() -> new ApplyException(APPLY_NOT_FOUND));
        apply.setIsRecruited(true);
    }
}
