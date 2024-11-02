package land.leets.Carrot.domain.user.controller;

import static land.leets.Carrot.domain.user.controller.ResponseMessage.BASIC_INFO_UPDATE_SUCCESS;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import land.leets.Carrot.domain.user.dto.request.BasicInfoUpdateRequest;
import land.leets.Carrot.domain.user.service.UserProfileService;
import land.leets.Carrot.global.auth.annotation.CurrentUser;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;

    @PatchMapping("/update-basic-info")
    @Operation(summary = "프로필 기본 정보 수정")
    public ResponseEntity<ResponseDto<Void>> updateBasicInfo(@RequestBody @Valid BasicInfoUpdateRequest request,
                                                             @CurrentUser Long userId) {
        userProfileService.updateBasicInfo(request, userId);
        return ResponseEntity.ok(
                ResponseDto.response(BASIC_INFO_UPDATE_SUCCESS.getCode(), BASIC_INFO_UPDATE_SUCCESS.getMessage())
        );
    }
}
