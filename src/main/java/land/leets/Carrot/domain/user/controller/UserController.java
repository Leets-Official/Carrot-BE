package land.leets.Carrot.domain.user.controller;

import static land.leets.Carrot.domain.user.controller.ResponseMessage.LOGIN_SUCCESS;
import static land.leets.Carrot.domain.user.controller.ResponseMessage.USER_SAVE_SUCCESS;
import static land.leets.Carrot.global.common.response.ResponseDto.response;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import land.leets.Carrot.domain.user.dto.request.CeoSignupRequest;
import land.leets.Carrot.domain.user.dto.request.EmployeeSignupRequest;
import land.leets.Carrot.domain.user.dto.request.LoginRequest;
import land.leets.Carrot.domain.user.service.CeoSignupService;
import land.leets.Carrot.domain.user.service.EmployeeSignupService;
import land.leets.Carrot.domain.user.service.LoginService;
import land.leets.Carrot.domain.user.service.UserService;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserController", description = "사용자 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final EmployeeSignupService employeeSignupService;
    private final CeoSignupService ceoSignupService;
    private final LoginService loginService;
    private final UserService userService;


    @GetMapping("/check-email-duplicate")
    @Operation(summary = "회원가입 이메일 중복검사")
    public ResponseEntity<ResponseDto<Void>> checkEmailDuplicate(@RequestParam String email) {
        userService.checkEmailDuplicate(email);
        return ResponseEntity.ok(
                response(USER_SAVE_SUCCESS.getCode(), USER_SAVE_SUCCESS.getMessage())
        );
    }

    @PostMapping("/employeeSignup")
    @Operation(summary = "구직자 회원가입")
    public ResponseEntity<ResponseDto<Void>> employeeSignup(@RequestBody @Valid EmployeeSignupRequest request) {
        employeeSignupService.employeeSignup(request);
        return ResponseEntity.ok(
                response(USER_SAVE_SUCCESS.getCode(), USER_SAVE_SUCCESS.getMessage())
        );
    }

    @PostMapping("/ceoSignup")
    @Operation(summary = "고용자 회원가입")
    public ResponseEntity<ResponseDto<Void>> ceoSignup(@RequestBody @Valid CeoSignupRequest request) {
        ceoSignupService.ceoSignup(request);
        return ResponseEntity.ok(
                response(USER_SAVE_SUCCESS.getCode(), USER_SAVE_SUCCESS.getMessage())
        );
    }

    @PostMapping("/login")
    @Operation(summary = "구직자, 고용자 로그인")
    public ResponseEntity<ResponseDto<Void>> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        loginService.authenticate(request.getEmail(), request.getPassword(), response);
        return ResponseEntity.ok(
                ResponseDto.response(LOGIN_SUCCESS.getCode(), LOGIN_SUCCESS.getMessage())
        );
    }
}