package user_common.application.exceptions;

import java.io.Serial;
import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(UUID userId) {
        super("Usuário com ID " + userId + " não encontrado.");
    }
}
