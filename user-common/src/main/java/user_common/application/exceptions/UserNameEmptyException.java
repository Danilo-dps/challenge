package user_common.application.exceptions;

import java.io.Serial;

public class UserNameEmptyException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserNameEmptyException() {
        super("Nome de usuário não pode ser vazio");
    }
}
