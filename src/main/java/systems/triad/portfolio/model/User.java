package systems.triad.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade que representa um usuário do sistema
 *
 * @author brunocarneiro
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "O usuário não pode ser vazio")
    @Size(min = 3, max = 20, message = "O usuário deve conter entre {min} e {max} caracteres")
    @Column(name = "USERNAME")
    private String username;

    @NotBlank(message = "O password não pode ser vazio")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "O password não atende às especificações definidas")
    @Column(name = "PASSWORD")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "active")
    private Boolean active;

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(this.createdAt)) {
            this.setCreatedAt(LocalDateTime.now());
        }
    }
}
