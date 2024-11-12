package land.leets.Carrot.domain.user.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    // 로그인 및 회원가입 관련
    EMAIL_CHECK_SUCCESS(HttpStatus.OK.value(), "이메일 중복 체크 성공."),
    USER_SAVE_SUCCESS(HttpStatus.CREATED.value(), "회원가입 성공."),
    LOGIN_SUCCESS(HttpStatus.OK.value(), "로그인 성공."),

    // 마이페이지(프로필) 관련
    PROFILE_CHECK_SUCCESS(HttpStatus.OK.value(), "프로필 조회 성공."),
    BASIC_INFO_UPDATE_SUCCESS(HttpStatus.OK.value(), "프로필 기본정보 수정 성공"),
    CAREER_ADD_SUCCESS(HttpStatus.OK.value(), "경력사항 추가 성공"),
    CAREER_DELETE_SUCCESS(HttpStatus.OK.value(), "경력사항 삭제 성공"),
    SELF_INTRO_UPDATE_SUCCESS(HttpStatus.OK.value(), "자기소개 정보 수정 성공"),
    ADDITIONAL_INFO_UPDATE_SUCCESS(HttpStatus.OK.value(), "추가 정보 수정 성공"),
    STRENGTH_UPDATE_SUCCESS(HttpStatus.OK.value(), "장점 정보 수정 성공"),

    // 이미지 업로드 관련
    IMAGE_UPLOAD_SUCCESS(HttpStatus.OK.value(), "이미지 업로드 성공."),
    IMAGE_UPDATE_SUCCESS(HttpStatus.OK.value(), "이미지 수정 성공."),
    IMAGE_DELETE_SUCCESS(HttpStatus.OK.value(), "이미지 삭제 성공.");

    private final int code;
    private final String message;

}
