package systems.triad.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import systems.triad.portfolio.model.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

}
