package domain.service.impl;

import domain.dto.UserDTO;
import domain.record.UserResponse;
import domain.service.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public UserDTO create(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getByUserId(UUID userId) {
        return null;
    }

    @Override
    public UserDTO updateInfo(UUID userId, UserResponse userResponse) {
        return null;
    }

    @Override
    public UserDTO deleteAccount(UUID userId) {
        return null;
    }
}
