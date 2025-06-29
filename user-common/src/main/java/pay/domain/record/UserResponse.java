package pay.domain.record;

import java.math.BigDecimal;
import java.util.UUID;

public record UserResponse(UUID idUser, String fullName, String userEmail, BigDecimal balance) {
}
