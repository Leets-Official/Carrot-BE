package land.leets.Carrot.domain.user.controller;

import static land.leets.Carrot.domain.user.controller.ResponseMessage.ADDITIONAL_INFO_UPDATE_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.BASIC_INFO_UPDATE_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.CAREER_UPDATE_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.IMAGE_UPLOAD_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.PROFILE_CHECK_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.SELF_INTRO_UPDATE_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.STRENGTH_UPDATE_SUCCESS;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import land.leets.Carrot.domain.image.service.S3ImageService;
import land.leets.Carrot.domain.user.dto.request.BasicInfoUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeAdditionalInfoUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeCareerUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeSelfIntroUpdateRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeStrengthUpdateRequest;
import land.leets.Carrot.domain.user.service.UserProfileService;
import land.leets.Carrot.global.auth.annotation.CurrentUser;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-profiles")
public class UserProfileController {
    private final UserProfileService userProfileService;
    private final S3ImageService s3ImageService;

    @GetMapping("/profile")
    @Operation(summary = "프로필 메인 페이지")
    public ResponseEntity<ResponseDto<?>> check(@Parameter(hidden = true) @CurrentUser Long userId) {
        var profileResponse = userProfileService.check(userId);
        return ResponseEntity.ok(ResponseDto.response(
                PROFILE_CHECK_SUCCESS.getCode(),
                PROFILE_CHECK_SUCCESS.getMessage(),
                profileResponse
        ));
    }

    @PatchMapping("/update-basic-info")
    @Operation(summary = "프로필 기본 정보 수정")
    public ResponseEntity<ResponseDto<Void>> updateBasicInfo(@RequestBody @Valid BasicInfoUpdateRequest request,
                                                             @Parameter(hidden = true) @CurrentUser Long userId) {
        userProfileService.updateBasicInfo(request, userId);
        return ResponseEntity.ok(
                ResponseDto.response(BASIC_INFO_UPDATE_SUCCESS.getCode(),
                        BASIC_INFO_UPDATE_SUCCESS.getMessage())
        );
    }

    @PatchMapping("/update-career")
    @Operation(summary = "구직자 경력 수정")
    public ResponseEntity<ResponseDto<Void>> updateCareer(@RequestBody @Valid EmployeeCareerUpdateRequest request,
                                                          @Parameter(hidden = true) @CurrentUser Long userId) {
        userProfileService.updateCareer(request, userId);
        return ResponseEntity.ok(
                ResponseDto.response(CAREER_UPDATE_SUCCESS.getCode(),
                        CAREER_UPDATE_SUCCESS.getMessage())
        );
    }

    @PatchMapping("/update-self-introduction")
    @Operation(summary = "구직자 자기 소개 수정")
    public ResponseEntity<ResponseDto<Void>> updateSelfIntro(
            @RequestBody @Valid EmployeeSelfIntroUpdateRequest request,
            @Parameter(hidden = true) @CurrentUser Long userId) {
        userProfileService.updateSelfIntro(request, userId);
        return ResponseEntity.ok(
                ResponseDto.response(SELF_INTRO_UPDATE_SUCCESS.getCode(),
                        SELF_INTRO_UPDATE_SUCCESS.getMessage())
        );
    }

    @PatchMapping("/update-additional-info")
    @Operation(summary = "구직자 추가 정보 수정")
    public ResponseEntity<ResponseDto<Void>> updateAdditionalInfo(
            @RequestBody @Valid EmployeeAdditionalInfoUpdateRequest request,
            @Parameter(hidden = true) @CurrentUser Long userId) {
        userProfileService.updateAdditionalInfo(request, userId);
        return ResponseEntity.ok(
                ResponseDto.response(ADDITIONAL_INFO_UPDATE_SUCCESS.getCode(),
                        ADDITIONAL_INFO_UPDATE_SUCCESS.getMessage())
        );
    }

    @PatchMapping("/update-strength")
    @Operation(summary = "구직자 나의 장점 수정")
    public ResponseEntity<ResponseDto<Void>> updateStrength(@RequestBody @Valid EmployeeStrengthUpdateRequest request,
                                                            @Parameter(hidden = true) @CurrentUser Long userId) {
        userProfileService.updateStrength(request, userId);
        return ResponseEntity.ok(
                ResponseDto.response(STRENGTH_UPDATE_SUCCESS.getCode(),
                        STRENGTH_UPDATE_SUCCESS.getMessage())
        );
    }

    @PostMapping("/upload-profile-image")
    @Operation(summary = "프로필 이미지 업로드")
    public ResponseEntity<ResponseDto<String>> uploadProfileImage(
            @RequestParam("image") MultipartFile image,
            @Parameter(hidden = true) @CurrentUser Long userId) {
        String imageUrl = s3ImageService.uploadImage(image, "profile-images");
        userProfileService.updateProfileImageUrl(userId, imageUrl);
        return ResponseEntity.ok(
                ResponseDto.response(IMAGE_UPLOAD_SUCCESS.getCode(),
                        IMAGE_UPLOAD_SUCCESS.getMessage(), imageUrl)
        );
    }
}
