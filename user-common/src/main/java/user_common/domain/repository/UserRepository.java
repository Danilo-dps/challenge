package user_common.domain.repository;

import user_common.domain.model.DepositHistory;
import user_common.domain.model.TransferHistory;
import user_common.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserEmail(String userEmail);
    Optional<User> findByCpf(String cpf);
    List<DepositHistory> findDepositByIdUser(UUID idUser);
    List<TransferHistory> findTransferByIdUser(UUID idUser);
}
