package systems.triad.portfolio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de parametrização de configurações do sistema
 *
 * @author brunocarneiro
 */
@RestController
@RequestMapping("/system")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SystemController {

    @PostMapping
    public ResponseEntity<String> config() {
        return ResponseEntity.ok("OK");
    }
}
