package land.leets.Carrot.domain.apply.service;

import static land.leets.Carrot.domain.apply.exception.ApplyErrorMessage.APPLY_NOT_FOUND;

import jakarta.transaction.Transactional;
import land.leets.Carrot.domain.apply.dto.request.PostApplyRequest;
import land.leets.Carrot.domain.apply.entity.Apply;
import land.leets.Carrot.domain.apply.exception.ApplyException;
import land.leets.Carrot.domain.apply.repository.ApplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplyService {
    private final ApplyRepository applyRepository;

    public void postApply(PostApplyRequest postApplyRequest) {
        Apply apply = new Apply(postApplyRequest.postId(), postApplyRequest.userId());
        applyRepository.save(apply);
    }

    public void setStatusRecruited(PostApplyRequest postApplyRequest) {
        Apply apply = applyRepository.findByPostIdAndUserID(postApplyRequest.postId(), postApplyRequest.userId())
                .orElseThrow(() -> new ApplyException(APPLY_NOT_FOUND));
        apply.setIsRecruited(true);
    }
}
