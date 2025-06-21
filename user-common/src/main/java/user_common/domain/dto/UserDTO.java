package user_common.domain.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID idUser;

    @NotBlank(message = "O nome completo é obrigatório")
    private String fullName;

    @CPF(message = "CPF inválido")
    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @Email(message = "Formato de e-mail inválido")
    @NotBlank(message = "O e-mail é obrigatório")
    private String userEmail;

    @Column(nullable = false)
    @Builder.Default
    private BigDecimal balance = BigDecimal.ZERO;

    public UserDTO(String fullName, String cpf, String userEmail, BigDecimal balance) {
        this.fullName = fullName;
        this.cpf = cpf;
        this.userEmail = userEmail;
        this.balance = BigDecimal.ZERO;
    }
}