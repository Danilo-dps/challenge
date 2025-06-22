package user_common.domain.service.impl;

import user_common.application.exceptions.*;
import user_common.domain.adapter.User2UserDTO;
import user_common.domain.adapter.User2UserResponse;
import user_common.domain.dto.UserDTO;
import user_common.domain.model.User;
import user_common.domain.record.UserResponse;
import user_common.domain.repository.UserRepository;
import user_common.domain.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import user_common.domain.service.validations.EmailValidator;
import user_common.domain.service.validations.UserValidator;

import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final EmailValidator emailValidator;

    public UserServiceImpl(UserRepository userRepository, UserValidator userValidator, EmailValidator emailValidator){
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.emailValidator = emailValidator;
    }

    @Override
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        logger.info("Criando login...");

        userValidator.validate(userDTO);

        if (userDTO.getFullName() == null || userDTO.getFullName().trim().isEmpty()) {
            logger.warning("Erro. Nome está vazio");
            throw new UserNameEmptyException();
        }
        if (userRepository.findByCpf(userDTO.getCpf()).isPresent()) {
            logger.warning("Erro. CPF já cadastrado");
            throw new DuplicateCPFException(userDTO.getCpf());
        }
        if (userRepository.findByUserEmail(userDTO.getUserEmail()).isPresent()) {
            logger.warning("Erro. Email já cadastrado");
            throw new DuplicateEmailException(userDTO.getUserEmail());
        }

        User user = User.builder().fullName(userDTO.getFullName()).userEmail(userDTO.getUserEmail()).cpf(userDTO.getCpf()).build();
        logger.info("Usuário criado!");
        User savedUser = userRepository.save(user);
        return User2UserDTO.convert(savedUser);
    }

    @Override
    @Transactional
    public UserResponse getById(UUID idUser) {
        Objects.requireNonNull(idUser, "User ID não pode ser null");
        logger.info("Procurando usuário...");
        return userRepository.findById(idUser)
                .map(User2UserResponse::convert)
                .orElseThrow(() -> {
                    logger.warning("Usuário não encontrado com ID: " + idUser);
                    return new UserNotFoundException(idUser);
                });
    }

    @Override
    @Transactional
    public UserResponse getByEmail(String userEmail) {
        Objects.requireNonNull(userEmail, "Email não pode ser null");
        logger.info("Procurando usuário...");
        return userRepository.findByUserEmail(userEmail)
                .map(User2UserResponse::convert)
                .orElseThrow(() -> {
                    logger.warning("Usuário não encontrado com Email: " + userEmail);
                    return new UserNotFoundException(userEmail);
                });
       }

    @Override
    @Transactional
    public UserDTO update(UUID userId, UserResponse userResponse) {
        logger.info("Atualizando dados...");
        User existingUser = userRepository.findById(userId).orElseThrow(() -> {logger.warning("Usuário não encontrado com ID: " + userId); return new UserNotFoundException(userId);});

        emailValidator.validate(userResponse.userEmail());

        if (userResponse.userEmail() != null
                && !userResponse.userEmail().equals(existingUser.getUserEmail())
                && userRepository.findByUserEmail(userResponse.userEmail()).isPresent()) {
                logger.warning("Erro. email já cadastrado");
                throw new DuplicateEmailException(userResponse.userEmail());
        }

        if (userResponse.fullName() != null && !userResponse.fullName().isBlank()) {
            existingUser.setFullName(userResponse.fullName());
        }

        if (userResponse.userEmail() != null && !userResponse.userEmail().isBlank()) {
            existingUser.setUserEmail(userResponse.userEmail());
        }

        logger.info("Usuário atualizado");
        User updatedUser = userRepository.save(existingUser);
        return User2UserDTO.convert(updatedUser);
    }

    @Override
    @Transactional
    public void delete(UUID userId) {
        logger.info("Verificando a existência do usuário para excluir...");
        if (!userRepository.existsById(userId)) {
            logger.warning("Erro. Usuário não encontrado");
            throw new UserNotFoundException(userId);
        }

        logger.info("Usuário excluído");
        userRepository.deleteById(userId);
    }
}
