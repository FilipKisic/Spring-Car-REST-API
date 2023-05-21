package hr.algebra.springproject.controller;

import hr.algebra.springproject.model.AuthRequest;
import hr.algebra.springproject.service.JwtGeneratorServiceImpl;
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

    private JwtGeneratorServiceImpl jwtGeneratorService;


    @PostMapping("/login")
    public String authenticate(@RequestBody final AuthRequest request) {
        if (!Objects.equals(request, new AuthRequest("filip", "password"))) {
            throw new UsernameNotFoundException("User not found...");
        }
        return jwtGeneratorService.generateToken(request.username());
    }
}
