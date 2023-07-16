package systems.triad.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import systems.triad.portfolio.model.User;

/**
 * Repositório de usuários
 *
 * @author brunocarneiro
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
