package au.com.vietnamesecolour.controller;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponseUtils;
import au.com.vietnamesecolour.dto.AuthenticationRequestDTO;
import au.com.vietnamesecolour.dto.AuthenticationResponseDTO;
import au.com.vietnamesecolour.dto.UserRegisterRequestDTO;
import au.com.vietnamesecolour.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

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

}
