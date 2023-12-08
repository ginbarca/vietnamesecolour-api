package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.config.exception.CommonErrorCode;
import au.com.vietnamesecolour.dto.AuthenticationRequestDTO;
import au.com.vietnamesecolour.dto.AuthenticationResponseDTO;
import au.com.vietnamesecolour.dto.ResetPasswordRequestDTO;
import au.com.vietnamesecolour.dto.UserRegisterRequestDTO;
import au.com.vietnamesecolour.service.AuthenticationService;
import au.com.vietnamesecolour.service.UserService;
import au.com.vietnamesecolour.utils.CommonUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthenticationService service;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<AuthenticationResponseDTO>> register(@RequestBody UserRegisterRequestDTO request) {
        ResponseData<AuthenticationResponseDTO> responseData = service.register(request);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseData<AuthenticationResponseDTO>> authenticate(@RequestBody AuthenticationRequestDTO request) {
        ResponseData<AuthenticationResponseDTO> responseData = service.authenticate(request);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseData<AuthenticationResponseDTO>> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData<AuthenticationResponseDTO> responseData = service.refreshToken(request, response);
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping("/send-reset-password-link")
    public ResponseEntity<ResponseData<Void>> sendResetPwdLink(@RequestBody ResetPasswordRequestDTO pwdReqDTO, HttpServletRequest servletRequest) throws MessagingException, UnsupportedEncodingException {
        Boolean isUserExist = userService.existsByEmail(pwdReqDTO.getEmail());
        ResponseData<Void> responseData;
        if (isUserExist) {
            responseData = userService.sendResetPwdLink(CommonUtils.getApplicationUrl(servletRequest), pwdReqDTO.getEmail());
        } else {
            responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.ENTITY_NOT_FOUND.getMessage());
        }
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResponseData<Void>> resetPassword(@RequestBody ResetPasswordRequestDTO pwdReqDTO, @Valid @RequestParam("token") String token) {
        Boolean isUserExist = userService.existsByEmail(pwdReqDTO.getEmail());
        ResponseData<Void> responseData;
        if (isUserExist) {
            responseData = userService.resetPassword(pwdReqDTO, token);
        } else {
            responseData = new ResponseData<>(ResponseStatusCode.NOT_FOUND.getCode(), CommonErrorCode.ENTITY_NOT_FOUND.getMessage());
        }
        return ResponseUtils.status(responseData.getCode(), responseData.getMessage(), responseData.getData(), HttpStatus.valueOf(responseData.getCode()));
    }
}
