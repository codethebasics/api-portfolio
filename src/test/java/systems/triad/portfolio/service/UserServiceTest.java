package systems.triad.portfolio.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import systems.triad.portfolio.model.User;
import systems.triad.portfolio.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Testes de integração do serviço de usuário
 *
 * @author brunocarneiro
 */
@DataJpaTest
class UserServiceTest {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        this.userService = new UserService(this.userRepository);
    }

    @Test
    void givenUser_whenSave_thenReturnSavedUser() {

        // given
        User user = User.builder()
                .active(true)
                .username("bruno.carneiro")
                .password("123456")
                .build();

        // when
        User response = this.userService.save(user);

        // then
        assertNotNull(response, "O usuário não deveria estar nulo");
        assertNotNull(response.getCreatedAt(), "A data de criação não deveria estar nula");
    }

    @Test
    void whenFindAll_thenReturnUser() {

        // when
        List<User> response = this.userService.findAll();

        // then
        assertNotNull(response, "O retorno não deveria ser nulo");
    }

    @Test
    void givenUserId_whenFindById_thenReturnUser() {

        // given
        Long id = 1L;

        // when
        User response = this.userService.findById(id);

        // then
        assertNotNull(response, "O retorno não deveria ser nulo");
    }
}