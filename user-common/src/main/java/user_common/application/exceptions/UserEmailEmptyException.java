package user_common.application.exceptions;

import java.io.Serial;

public class UserEmailEmptyException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserEmailEmptyException() {
        super("Email de usuário não pode ser vazio");
    }
}
