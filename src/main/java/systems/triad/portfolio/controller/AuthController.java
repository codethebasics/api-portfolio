package systems.triad.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de autenticação
 *
 * @author brunocarneiro
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    public ResponseEntity<String> authenticate() {
        return ResponseEntity.ok("OK");
    }
}
