package land.leets.Carrot.domain.apply.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import land.leets.Carrot.domain.apply.dto.request.ApplyRequest;
import land.leets.Carrot.domain.apply.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ApplyController", description = "지원 관련 controller")
@RequiredArgsConstructor
@RequestMapping("/api/v1/apply")
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping
    public ResponseEntity<Void> postApply(@RequestBody ApplyRequest requestBody) {
        applyService.postApply(requestBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("")
    public ResponseEntity<Void> postEmployedUser(@RequestBody ApplyRequest request){
        applyService.setStatusRecruited(request);
        return ResponseEntity.ok().build();
    }
}