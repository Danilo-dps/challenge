package domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID idUser;
    private String fullName;
    private String cpf;
    private String email;
}