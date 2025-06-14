package user_common.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID idUser;

    @NotBlank()
    @Column(nullable = false, length = 100)
    private String fullName;

    @NotBlank()
    @Column(nullable = false, length = 100)
    private String cpf;

    @NotBlank()
    @Column(nullable = false, length = 50)
    private String email;
}
