package user_common.domain.adapter;

import user_common.domain.dto.TransferHistoryDTO;
import user_common.domain.model.TransferHistory;

public class TransferHistory2TransferHistoryDTO {

    private TransferHistory2TransferHistoryDTO() {}

    public static TransferHistoryDTO convert(TransferHistory transferHistory){
        return new TransferHistoryDTO(transferHistory.getTransferId(),
                transferHistory.getWhenDidItHappen(),
                transferHistory.getDestinationEmail(),
                transferHistory.getOperationType(),
                transferHistory.getAmount(),
                User2UserDTO.convert(transferHistory.getUser()));
    }
}
