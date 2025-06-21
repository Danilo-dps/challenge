package user_common.domain.adapter;

import user_common.domain.dto.TransferHistoryDTO;
import user_common.domain.model.TransferHistory;

public class TransferHistoryDTO2TransferHistory {

    private TransferHistoryDTO2TransferHistory() {}

    public static TransferHistory convert(TransferHistoryDTO transferHistoryDTO){
        return new TransferHistory(transferHistoryDTO.getTransferId(),
                transferHistoryDTO.getWhenDidItHappen(),
                transferHistoryDTO.getDestinationEmail(),
                transferHistoryDTO.getOperationType(),
                transferHistoryDTO.getAmount(),
                UserDTO2User.convert(transferHistoryDTO.getUserDTO()));
    }
}
