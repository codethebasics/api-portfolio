package systems.triad.portfolio.service;

import org.springframework.stereotype.Service;
import systems.triad.portfolio.model.User;
import systems.triad.portfolio.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long id) {
        return this.userRepository
                .findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }
}
