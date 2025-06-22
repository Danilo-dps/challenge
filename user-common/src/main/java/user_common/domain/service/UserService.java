package user_common.domain.service;

import user_common.domain.dto.UserDTO;
import user_common.domain.record.DepositResponse;
import user_common.domain.record.TransferResponse;
import user_common.domain.record.UserResponse;

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
