package user_common.application.exceptions;

import java.io.Serial;
import java.util.UUID;

public class InsufficientBalanceException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public InsufficientBalanceException() {
        super("Erro. Saldo disponível é insuficiente");
    }

}
