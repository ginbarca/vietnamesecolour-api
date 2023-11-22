package au.com.vietnamesecolour.service;

import au.com.vietnamesecolour.config.data.ResponseData;
import au.com.vietnamesecolour.config.data.ResponseStatusCode;
import au.com.vietnamesecolour.dto.AuthenticationRequestDTO;
import au.com.vietnamesecolour.dto.AuthenticationResponseDTO;
import au.com.vietnamesecolour.dto.UserDetailDTO;
import au.com.vietnamesecolour.dto.UserRegisterRequestDTO;
import au.com.vietnamesecolour.entity.Role;
import au.com.vietnamesecolour.entity.Token;
import au.com.vietnamesecolour.entity.TokenType;
import au.com.vietnamesecolour.entity.User;
import au.com.vietnamesecolour.repos.RoleRepository;
import au.com.vietnamesecolour.repos.TokenRepository;
import au.com.vietnamesecolour.repos.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseData<AuthenticationResponseDTO> register(UserRegisterRequestDTO request) {
        ResponseData<AuthenticationResponseDTO> responseData;
        Set<Role> roleSet = new HashSet<>();
        for (Integer roleId : request.getRoleIds()) {
            Optional<Role> role = roleRepository.findById(roleId);
            if (role.isEmpty()) {
                responseData = new ResponseData<>(ResponseStatusCode.BAD_REQUEST.getCode(), "There is no role with ID " + roleId);
                return responseData;
            }
            roleSet.add(role.get());
        }
        responseData = new ResponseData<>();
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .mobile(request.getMobile())
                .gender(request.getGender())
                .roles(roleSet)
                .enabled(Boolean.TRUE)
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        responseData.setData(
                AuthenticationResponseDTO.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .userDetailDTO(
                                UserDetailDTO.builder()
                                        .firstname(user.getFirstname())
                                        .lastname(user.getLastname())
                                        .email(user.getEmail())
                                        .username(user.getUsername())
                                        .gender(user.getGender())
                                        .mobile(user.getMobile())
                                        .roleName(user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                                        .build()
                        )
                        .build()
        );
        return responseData;
    }

    public ResponseData<AuthenticationResponseDTO> authenticate(AuthenticationRequestDTO request) {
        ResponseData<AuthenticationResponseDTO> responseData = new ResponseData<>();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        responseData.setData(
                AuthenticationResponseDTO.builder()
                        .accessToken(jwtToken)
                        .refreshToken(refreshToken)
                        .userDetailDTO(
                                UserDetailDTO.builder()
                                        .firstname(user.getFirstname())
                                        .lastname(user.getLastname())
                                        .email(user.getEmail())
                                        .username(user.getUsername())
                                        .gender(user.getGender())
                                        .mobile(user.getMobile())
                                        .roleName(user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                                        .build()
                        )
                        .build()
        );
        return responseData;
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public ResponseData<AuthenticationResponseDTO> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseData<AuthenticationResponseDTO> responseData = new ResponseData<>();
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        refreshToken = authHeader.substring(7);
        username = jwtService.extractUsername(refreshToken);
        if (username != null) {
            var user = this.repository.findByUsername(username)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthenticationResponseDTO authResponse = AuthenticationResponseDTO.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                responseData.setData(authResponse);
            }
        }
        return responseData;
    }
}
