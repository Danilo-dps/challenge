package user_common.domain.adapter;

import user_common.domain.dto.DepositHistoryDTO;
import user_common.domain.model.DepositHistory;

public class DepositHistory2DepositHistoryDTO {

    private DepositHistory2DepositHistoryDTO() {}

    public static DepositHistoryDTO convert(DepositHistory depositHistory){
        return new DepositHistoryDTO(depositHistory.getDepositId(),
                depositHistory.getWhenDidItHappen(),
                depositHistory.getOperationType(),
                depositHistory.getAmount(),
                User2UserDTO.convert(depositHistory.getUser()));
    }
}
