package user_common.domain.service.impl;

import user_common.application.exceptions.UserNotFoundException;
import user_common.domain.adapter.User2UserDTO;
import user_common.domain.dto.UserDTO;
import user_common.domain.model.User;
import user_common.domain.record.UserResponse;
import user_common.domain.repository.UserRepository;
import user_common.domain.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        Objects.requireNonNull(userDTO, "UserDTO não pode ser nulo");
        User user = User.builder()
                .fullName(userDTO.getFullName())
                .email(userDTO.getEmail())
                .cpf(userDTO.getCpf())
                .build();
        User savedUser = userRepository.save(user);

        return User2UserDTO.convert(savedUser);
    }

    @Override
    @Transactional
    public UserDTO getByUserId(UUID userId) {
        Objects.requireNonNull(userId, "User ID não pode ser null");

        return userRepository.findById(userId)
                .map(User2UserDTO::convert)
                .orElseThrow(() -> new UserNotFoundException(userId));
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
