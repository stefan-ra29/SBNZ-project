package sbnz.integracija.example.security;

import demo.facts.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sbnz.integracija.example.exceptions.CustomBadRequestException;
import sbnz.integracija.example.user.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @PostMapping()
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new CustomBadRequestException("Invalid email or password");
        }
        User user = userService.getUserByUsername(authRequest.getUsername());
        return jwtUtil.generateToken(authRequest.getUsername(), user.getName(), user.getId());
    }
}

