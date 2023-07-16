package systems.triad.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Index controller
 *
 * @author brunocarneiro
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body("Index");
    }

    @PostMapping
    public ResponseEntity<String> post() {
        return ResponseEntity.status(HttpStatus.OK).body("Index");
    }
}
