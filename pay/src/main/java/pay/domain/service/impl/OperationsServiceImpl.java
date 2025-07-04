package pay.domain.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pay.application.exceptions.InsufficientBalanceException;
import pay.application.exceptions.InvalidValueException;
import pay.application.exceptions.NotFoundException;
import pay.domain.adapter.DepositHistory2DepositResponse;
import pay.domain.adapter.TransferHistory2TransferResponse;
import pay.domain.dto.DepositRequestDTO;
import pay.domain.dto.TransferRequestDTO;
import pay.domain.model.DepositHistory;
import pay.domain.model.TransferHistory;
import pay.domain.model.User;
import pay.domain.record.DepositResponse;
import pay.domain.record.TransferResponse;
import pay.domain.repository.DepositHistoryRepository;
import pay.domain.repository.TransferHistoryRepository;
import pay.domain.repository.UserRepository;
import pay.domain.service.OperationsService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

        User user = userRepository.findByEmail(requestDeposit.getUserEmail())
                .orElseThrow(() -> new NotFoundException(requestDeposit.getUserEmail()));

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

        User fromUser = userRepository.findByEmail(requestTransfer.getUserEmail()).orElseThrow(() -> new NotFoundException(requestTransfer.getUserEmail()));
        User destinationUser = userRepository.findByEmail(requestTransfer.getDestinationEmail()).orElseThrow(() -> new NotFoundException(requestTransfer.getDestinationEmail()));

        if (fromUser.getBalance() == null || fromUser.getBalance().compareTo(requestTransfer.getAmount()) < 0) {
            throw new InsufficientBalanceException();
        }

        fromUser.setBalance(fromUser.getBalance().subtract(requestTransfer.getAmount()));
        destinationUser.setBalance(destinationUser.getBalance().add(requestTransfer.getAmount()));

        userRepository.save(fromUser);
        userRepository.save(destinationUser);

        LocalDateTime now = LocalDateTime.now();

        TransferHistory transfer = new TransferHistory(now, destinationUser.getEmail(),"Transferência", requestTransfer.getAmount(), fromUser);
        transferHistoryRepository.save(transfer);

        return TransferHistory2TransferResponse.convert(transfer);
    }

}
