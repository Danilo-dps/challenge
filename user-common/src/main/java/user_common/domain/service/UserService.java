package user_common.domain.service;

import user_common.domain.dto.UserDTO;
import user_common.domain.record.UserResponse;

import java.util.UUID;

public interface UserService {

    UserDTO create(UserDTO userDTO);
    UserDTO getByUserId(UUID userId);
    UserDTO updateInfo(UUID userId, UserResponse userResponse);
    UserDTO deleteAccount(UUID userId);
}
