package systems.triad.portfolio.service;

import org.springframework.security.core.userdetails.UserDetails;
import systems.triad.portfolio.model.AuthenticationUser;
import systems.triad.portfolio.model.User;

import javax.security.auth.login.CredentialException;

public interface AuthenticationService {

    AuthenticationUser login(User user) throws CredentialException;

}
