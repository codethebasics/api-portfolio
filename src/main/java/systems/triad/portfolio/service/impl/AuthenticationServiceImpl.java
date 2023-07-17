package systems.triad.portfolio.service.impl;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import systems.triad.portfolio.model.AuthenticationUser;
import systems.triad.portfolio.model.User;
import systems.triad.portfolio.service.AuthenticationService;
import systems.triad.portfolio.service.UserService;

import javax.security.auth.login.CredentialException;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthenticationUser login(User user) throws CredentialException {
        try {
            User userFromDatabase = Optional.of(this.userService.findByUsername(user.getUsername()))
                    .orElseThrow(Exception::new);
            if (!this.passwordEncoder.matches(user.getPassword(), userFromDatabase.getPassword())) {
                throw new CredentialException("Credenciais inválidas");
            }
            return new AuthenticationUser(userFromDatabase);
        } catch (Exception e) {
            throw new CredentialException(e.getMessage());
        }
    }
}
