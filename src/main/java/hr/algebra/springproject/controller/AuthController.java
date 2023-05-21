package hr.algebra.springproject.controller;

import hr.algebra.springproject.entity.RefreshToken;
import hr.algebra.springproject.model.AuthRequest;
import hr.algebra.springproject.model.AuthResponse;
import hr.algebra.springproject.model.RefreshTokenRequest;
import hr.algebra.springproject.service.security.JwtGeneratorService;
import hr.algebra.springproject.service.security.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {

    private JwtGeneratorService jwtGeneratorService;

    private RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public AuthResponse authenticate(@RequestBody final AuthRequest request) {
        if (!Objects.equals(request, new AuthRequest("filip", "password"))) {
            throw new UsernameNotFoundException("User not found...");
        }
        final RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.username());
        return AuthResponse.builder()
                .accessToken(jwtGeneratorService.generateToken(request.username()))
                .refreshToken(refreshToken.getToken())
                .build();
    }

    @PostMapping("/refreshToken")
    public AuthResponse refreshToken(@RequestBody final RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.token())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUsername)
                .map(username -> {
                    String accessToken = jwtGeneratorService.generateToken(username);
                    return AuthResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(request.token())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh token doesn't exist."));

    }
}
