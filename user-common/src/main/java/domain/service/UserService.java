package domain.service;

import domain.dto.UserDTO;
import domain.record.UserResponse;

import java.util.UUID;

public interface UserService {

    UserDTO create(UserDTO userDTO);
    UserDTO getByUserId(UUID userId);
    UserDTO updateInfo(UUID userId, UserResponse userResponse);
    UserDTO deleteAccount(UUID userId);
}
