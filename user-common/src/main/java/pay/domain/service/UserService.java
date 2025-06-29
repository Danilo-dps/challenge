package pay.domain.service;

import pay.domain.dto.UserDTO;
import pay.domain.record.DepositResponse;
import pay.domain.record.TransferResponse;
import pay.domain.record.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDTO create(UserDTO userDTO);
    UserResponse getById(UUID idUser);
    UserResponse getByEmail(String userEmail);
    UserDTO update(UUID userId, UserResponse userResponse);
    void delete(UUID userId);
    List<DepositResponse> getAllDeposits(UUID idUser);
    List<TransferResponse> getAllTransfers(UUID idUser);
}
