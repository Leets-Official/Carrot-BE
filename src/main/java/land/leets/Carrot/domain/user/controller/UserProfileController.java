package land.leets.Carrot.domain.user.controller;

import static land.leets.Carrot.domain.user.controller.ResponseMessage.ADDITIONAL_INFO_UPDATE_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.BASIC_INFO_UPDATE_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.CAREER_UPDATE_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.IMAGE_DELETE_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.IMAGE_UPDATE_SUCCESS;
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
import land.leets.Carrot.domain.user.dto.response.EmployeeProfileResponse;
import land.leets.Carrot.domain.user.dto.response.GetCeoInfoResponse;
import land.leets.Carrot.domain.user.dto.response.UserBasicInfoResponse;
import land.leets.Carrot.domain.user.service.CeoInfoService;
import land.leets.Carrot.domain.user.service.UserProfileService;
import land.leets.Carrot.global.auth.annotation.CurrentUser;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final CeoInfoService ceoInfoService;

    /*
       공통 필드
     */
    @GetMapping("/profile")
    @Operation(summary = "프로필 기본정보 조회")
    public ResponseEntity<ResponseDto<UserBasicInfoResponse>> check(
            @Parameter(hidden = true) @CurrentUser Long userId) {
        UserBasicInfoResponse userBasicInfoResponse = userProfileService.check(userId);
        return ResponseEntity.ok(ResponseDto.response(
                PROFILE_CHECK_SUCCESS.getCode(),
                PROFILE_CHECK_SUCCESS.getMessage(),
                userBasicInfoResponse
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

    @PostMapping("/upload-profile-image")
    @Operation(summary = "프로필 이미지 업로드")
    public ResponseEntity<ResponseDto<Void>> uploadProfileImage(
            @RequestParam("image") MultipartFile image,
            @Parameter(hidden = true) @CurrentUser Long userId) {
        String imageUrl = s3ImageService.uploadImage(image, "profile-images");
        userProfileService.updateProfileImageUrl(userId, imageUrl);
        return ResponseEntity.ok(
                ResponseDto.response(IMAGE_UPLOAD_SUCCESS.getCode(),
                        IMAGE_UPLOAD_SUCCESS.getMessage())
        );
    }

    @PatchMapping("/update-profile-image")
    @Operation(summary = "프로필 이미지 수정")
    public ResponseEntity<ResponseDto<Void>> updateProfileImage(
            @RequestParam("image") MultipartFile image,
            @RequestParam("oldFileName") String oldFileName,
            @Parameter(hidden = true) @CurrentUser Long userId) {
        String imageUrl = s3ImageService.updateImage(image, oldFileName, "profile-images");
        userProfileService.updateProfileImageUrl(userId, imageUrl);
        return ResponseEntity.ok(
                ResponseDto.response(IMAGE_UPDATE_SUCCESS.getCode(),
                        IMAGE_UPDATE_SUCCESS.getMessage())
        );
    }

    @DeleteMapping("/delete-profile-image")
    @Operation(summary = "프로필 이미지 삭제")
    public ResponseEntity<ResponseDto<Void>> deleteProfileImage(
            @RequestParam("fileName") String fileName,
            @Parameter(hidden = true) @CurrentUser Long userId) {
        s3ImageService.deleteImage(fileName);
        userProfileService.updateProfileImageUrl(userId, null);
        return ResponseEntity.ok(
                ResponseDto.response(IMAGE_DELETE_SUCCESS.getCode(),
                        IMAGE_DELETE_SUCCESS.getMessage())
        );
    }

    /*
             employee(구직자 프로필 관련)
     */
    @GetMapping("/employee-main-profile")
    @Operation(summary = "내 지원서 관리")
    public ResponseEntity<ResponseDto<EmployeeProfileResponse>> employeeAll(
            @Parameter(hidden = true) @CurrentUser Long userId) {
        EmployeeProfileResponse employeeProfileResponse = userProfileService.employeeAll(userId);
        return ResponseEntity.ok(ResponseDto.response(
                PROFILE_CHECK_SUCCESS.getCode(),
                PROFILE_CHECK_SUCCESS.getMessage(),
                employeeProfileResponse
        ));
    }

    @PatchMapping("/update-career")
    @Operation(summary = "구직자 경력사항 추가")
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

    /*
           ceo(고용자 프로필 관련)
    */
    @GetMapping("/ceo-info/{ceoId}")
    public ResponseEntity<ResponseDto<GetCeoInfoResponse>> getCeoInfo(@PathVariable("ceoId") Long ceoId) {
        return ResponseEntity.ok(ceoInfoService.getCeoInfo(ceoId));
    }
}
