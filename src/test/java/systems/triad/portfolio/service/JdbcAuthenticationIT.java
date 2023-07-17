package systems.triad.portfolio.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import systems.triad.portfolio.model.AuthenticationUser;
import systems.triad.portfolio.model.Authorities;
import systems.triad.portfolio.model.User;
import systems.triad.portfolio.service.impl.AuthenticationServiceImpl;

import javax.security.auth.login.CredentialException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JdbcAuthenticationIT {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthoritiesService authoritiesService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Test
    @DisplayName("Dado um usuário, quando salvar então retorna usuário salvo")
    @Order(1)
    @Commit
    void givenAuthority_whenSave_thenReturnSavedAuthority() {

        // given
        Authorities authority = Authorities.builder()
                .authority("ROLE_ADMIN")
                .build();

        // when
        Authorities response = this.authoritiesService.save(authority);

        // then
        assertNotNull(response, "O usuário salvo não deveria estar vazio");
        assertEquals(response.getAuthority(), "ROLE_ADMIN", "O password deveria estar criptografado");
    }

    @Test
    @DisplayName("Dado um usuário e uma permissão, quando salvar então retorna usuário com permissão salvo")
    @Order(2)
    @Commit
    void givenUserAndAuthority_whenAddAuthorityToUserAndSave_thenReturnUserWithAuthority() {

        // given
        Authorities authority = Authorities.builder()
                .authority("ROLE_ADMIN")
                .build();

        final String password = "123456";
        User user = User.builder()
                .username("bruno.carneiro")
                .password(this.passwordEncoder.encode(password))
                .enabled(true)
                .authorities(Collections.singletonList(authority))
                .build();

        // when
        User response = this.userService.save(user);

        // then
        assertNotNull(response, "O usuário salvo não deveria estar vazio");
        assertNotNull(response.getAuthorities(), "As permissões do usuário não deveria estar nula");
        assertTrue(response.getAuthorities().size() > 0, "A lista de permissões do usuário não deveria estar vazia");
        assertEquals("ROLE_ADMIN", response.getAuthorities().get(0).getAuthority());
    }

    @Test
    @Order(3)
    void givenId_whenFindById_thenReturnUserWithAuthority() {

        // given
        Long id = 1L;

        // when
        User response = this.userService.findById(id);

        // then
        assertNotNull(response, "O usuário não deveria estar nulo");
        assertNotNull(response.getAuthorities(), "A lista de permissões do usuário não deveria estar nula");
        assertEquals("ROLE_ADMIN", response.getAuthorities().get(0).getAuthority());
    }

    @Test
    @Order(4)
    void givenUserWithCorrectCredentials_whenAuthenticate_thenReturnHttp200() throws CredentialException {

        // given
        User user = User.builder()
                .username("bruno.carneiro")
                .password("123456")
                .build();

        // when
        AuthenticationUser authenticationUser = this.authenticationService.login(user);

        // then
        boolean isPasswordMatches = this.passwordEncoder.matches(user.getPassword(), authenticationUser.getPassword());
        assertNotNull(authenticationUser, "O usuário de autenticação não deveria estar nulo");
        assertTrue(isPasswordMatches, "O password não confere");
    }

    @Test
    @Order(5)
    void givenUserWithWrongCredentials_whenAuthenticate_thenThrowsCredentialsException() {
        // given
        User user = User.builder()
                .username("bruno.carneiro")
                .password("1234567")
                .build();

        // when / then
        assertThrows(CredentialException.class,  () -> {
            this.authenticationService.login(user);
        });
    }

}