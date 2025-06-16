package user_common.domain.service.impl;

import user_common.application.exceptions.DuplicateEmailException;
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
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

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
    public UserDTO getById(UUID userId) {
        Objects.requireNonNull(userId, "User ID não pode ser null");

        return userRepository.findById(userId)
                .map(User2UserDTO::convert)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public UserDTO update(UUID userId, UserResponse userResponse) {
        Objects.requireNonNull(userId, "User ID não pode ser null");

        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        if (userResponse.email() != null
                && !userResponse.email().equals(existingUser.getEmail())
                && userRepository.findByEmail(userResponse.email()).isPresent()) {
                throw new DuplicateEmailException(userResponse.email());
        }

        if (userResponse.fullName() != null && !userResponse.fullName().isBlank()) {
            existingUser.setFullName(userResponse.fullName());
        }

        if (userResponse.email() != null && !userResponse.email().isBlank()) {
            existingUser.setEmail(userResponse.email());
        }

        logger.info("Usuário atualizado");
        User updatedUser = userRepository.save(existingUser);
        return User2UserDTO.convert(updatedUser);
    }

    @Override
    public void delete(UUID userId) {
        logger.info("Verificando a existência do usuário para excluir");
        if (!userRepository.existsById(userId)) {
            logger.warning("Erro. Usuário não encontrado");
            throw new UserNotFoundException(userId);
        }

        logger.info("Produto excluído");
        userRepository.deleteById(userId);
    }
}
