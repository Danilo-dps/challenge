package user_common.domain.service;

import user_common.domain.dto.DepositHistoryDTO;
import user_common.domain.dto.DepositRequestDTO;
import user_common.domain.dto.TransferHistoryDTO;
import user_common.domain.dto.TransferRequestDTO;
import user_common.domain.record.DepositResponse;
import user_common.domain.record.TransferResponse;

public interface OperationsService {

    DepositResponse deposit(DepositRequestDTO requestDeposit);
    TransferResponse transfer(TransferRequestDTO requestTransfer);
}
