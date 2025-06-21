package user_common.domain.service;

import user_common.domain.dto.DepositHistoryDTO;
import user_common.domain.dto.DepositRequestDTO;
import user_common.domain.dto.TransferHistoryDTO;
import user_common.domain.dto.TransferRequestDTO;

public interface OperationsService {

    DepositHistoryDTO deposit(DepositRequestDTO requestDeposit);
    TransferHistoryDTO transfer(TransferRequestDTO requestTransfer);
}
