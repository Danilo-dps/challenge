package user_common.domain.service;

import user_common.domain.dto.UserDTO;
import user_common.domain.record.UserResponse;

import java.util.UUID;

public interface UserService {

    UserDTO create(UserDTO userDTO);
    UserResponse getById(UUID idUser);
    UserResponse getByEmail(String userEmail);
    UserDTO update(UUID userId, UserResponse userResponse);
    void delete(UUID userId);
}
