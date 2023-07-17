package systems.triad.portfolio.service;

import org.springframework.stereotype.Service;
import systems.triad.portfolio.model.Authorities;
import systems.triad.portfolio.repository.AuthoritiesRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AuthoritiesService {

    private final AuthoritiesRepository authoritiesRepository;

    public AuthoritiesService(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    public Authorities save(Authorities authorities) {
        return this.authoritiesRepository.save(authorities);
    }

    public Authorities findById(Long id) {
        return this.authoritiesRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Authorities> findAll() {
        return this.authoritiesRepository.findAll();
    }
}
