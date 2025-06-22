package user_common.domain.service.validations;

import org.springframework.stereotype.Component;
import user_common.domain.dto.UserDTO;

@Component
public class UserValidator {

    private final CpfValidator cpfValidator;
    private final EmailValidator emailValidator;

    public UserValidator(CpfValidator cpfValidator, EmailValidator emailValidator) {
        this.cpfValidator = cpfValidator;
        this.emailValidator = emailValidator;
    }

    public void validate(UserDTO userDTO) {
        cpfValidator.validate(userDTO.getCpf());
        emailValidator.validate(userDTO.getUserEmail());
    }
}
