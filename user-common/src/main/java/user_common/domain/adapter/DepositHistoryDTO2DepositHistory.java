package user_common.domain.adapter;

import user_common.domain.dto.DepositHistoryDTO;
import user_common.domain.model.DepositHistory;

public class DepositHistoryDTO2DepositHistory {

    private DepositHistoryDTO2DepositHistory() {}

    public static DepositHistory convert(DepositHistoryDTO depositHistoryDTO){
        return new DepositHistory(depositHistoryDTO.getDepositId(),
                depositHistoryDTO.getWhenDidItHappen(),
                depositHistoryDTO.getOperationType(),
                depositHistoryDTO.getAmount(),
                UserDTO2User.convert(depositHistoryDTO.getUserDTO()));
    }
}
