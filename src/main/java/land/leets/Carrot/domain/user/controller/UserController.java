package land.leets.Carrot.domain.user.controller;

import static land.leets.Carrot.domain.user.controller.ResponseMessage.USER_SAVE_SUCCESS;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import land.leets.Carrot.domain.user.dto.request.EmployeeSignupRequest;
import land.leets.Carrot.domain.user.dto.request.LoginRequest;
import land.leets.Carrot.domain.user.service.LoginService;
import land.leets.Carrot.domain.user.service.UserCreateService;
import land.leets.Carrot.global.common.response.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "UserController", description = "사용자 관련 컨트롤러")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserCreateService userCreateService;
    private final LoginService loginService;

    @PostMapping("/employeeSignup")
    public ResponseEntity<ResponseDto<Void>> employeeSignup(@RequestBody @Valid EmployeeSignupRequest request) {
        userCreateService.employeeSignup(request);
        return ResponseEntity.ok(
                ResponseDto.response(USER_SAVE_SUCCESS.getCode(), USER_SAVE_SUCCESS.getMessage())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<Void>> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        ResponseDto<Void> result = loginService.authenticate(request.getEmail(), request.getPassword(), response);
        return ResponseEntity.status(result.getCode()).body(result);
    }
}