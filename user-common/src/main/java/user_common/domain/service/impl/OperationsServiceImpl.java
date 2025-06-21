package user_common.domain.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import user_common.application.exceptions.InsufficientBalanceException;
import user_common.application.exceptions.InvalidValueException;
import user_common.application.exceptions.UserNotFoundException;
import user_common.domain.adapter.DepositHistory2DepositHistoryDTO;
import user_common.domain.adapter.DepositHistory2DepositResponse;
import user_common.domain.adapter.TransferHistory2TransferHistoryDTO;
import user_common.domain.adapter.TransferHistory2TransferResponse;
import user_common.domain.dto.DepositHistoryDTO;
import user_common.domain.dto.DepositRequestDTO;
import user_common.domain.dto.TransferHistoryDTO;
import user_common.domain.dto.TransferRequestDTO;
import user_common.domain.model.DepositHistory;
import user_common.domain.model.TransferHistory;
import user_common.domain.model.User;
import user_common.domain.record.DepositResponse;
import user_common.domain.record.TransferResponse;
import user_common.domain.repository.DepositHistoryRepository;
import user_common.domain.repository.TransferHistoryRepository;
import user_common.domain.repository.UserRepository;
import user_common.domain.service.OperationsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OperationsServiceImpl implements OperationsService {
    private static final Logger logger = Logger.getLogger(OperationsServiceImpl.class.getName());

    private final UserRepository userRepository;
    private final TransferHistoryRepository transferHistoryRepository;
    private final DepositHistoryRepository depositHistoryRepository;

    public OperationsServiceImpl(UserRepository userRepository,
                                 TransferHistoryRepository transferHistoryRepository,
                                 DepositHistoryRepository depositHistoryRepository){
        this.userRepository = userRepository;
        this.transferHistoryRepository = transferHistoryRepository;
        this.depositHistoryRepository = depositHistoryRepository;
    }

    @Override
    @Transactional
    public DepositResponse deposit(DepositRequestDTO requestDeposit) {

        if (requestDeposit.getAmount() == null || requestDeposit.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidValueException();
        }

        User user = userRepository.findByUserEmail(requestDeposit.getUserEmail())
                .orElseThrow(() -> new UserNotFoundException(requestDeposit.getUserEmail()));

        user.setBalance(user.getBalance().add(requestDeposit.getAmount()));
        userRepository.save(user);

        DepositHistory deposit = new DepositHistory(LocalDateTime.now(), "Depósito", requestDeposit.getAmount(), user);
        depositHistoryRepository.save(deposit);

        return DepositHistory2DepositResponse.convert(deposit);
    }

    @Override
    @Transactional
    public TransferResponse transfer(TransferRequestDTO requestTransfer) {
        if (requestTransfer.getAmount() == null || requestTransfer.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidValueException();
        }

        User fromUser = userRepository.findByUserEmail(requestTransfer.getUserEmail()).orElseThrow(() -> new UserNotFoundException(requestTransfer.getUserEmail()));
        User destinationUser = userRepository.findByUserEmail(requestTransfer.getDestinationEmail()).orElseThrow(() -> new UserNotFoundException(requestTransfer.getDestinationEmail()));

        if (fromUser.getBalance() == null || fromUser.getBalance().compareTo(requestTransfer.getAmount()) < 0) {
            throw new InsufficientBalanceException();
        }

        fromUser.setBalance(fromUser.getBalance().subtract(requestTransfer.getAmount()));
        destinationUser.setBalance(destinationUser.getBalance().add(requestTransfer.getAmount()));

        userRepository.save(fromUser);
        userRepository.save(destinationUser);

        LocalDateTime now = LocalDateTime.now();

        TransferHistory transfer = new TransferHistory(now, destinationUser.getUserEmail(),"Transferência", requestTransfer.getAmount(), fromUser);
        transferHistoryRepository.save(transfer);

        return TransferHistory2TransferResponse.convert(transfer);
    }

}
