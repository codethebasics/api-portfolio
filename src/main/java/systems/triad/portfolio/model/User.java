package systems.triad.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "O usuário não pode ser vazio")
    @Size(min = 3, max = 50, message = "O usuário deve conter entre {min} e {max} caracteres")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "O password não pode ser vazio")
    @Column(name = "password")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(mappedBy = "users")
    private List<Authorities> authorities;

    @PrePersist
    public void prePersist() {
        if (Objects.isNull(this.createdAt)) {
            this.setCreatedAt(LocalDateTime.now());
        }
    }
}
