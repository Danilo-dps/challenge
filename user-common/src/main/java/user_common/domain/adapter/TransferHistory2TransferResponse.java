package user_common.domain.adapter;

import user_common.domain.model.TransferHistory;
import user_common.domain.record.TransferResponse;

public class TransferHistory2TransferResponse {

    private TransferHistory2TransferResponse(){}

    public static TransferResponse convert(TransferHistory transferHistory){
        return new TransferResponse(transferHistory.getTransferId(), transferHistory.getUser().getFullName(), transferHistory.getUser().getUserEmail(), transferHistory.getDestinationEmail(), transferHistory.getAmount(), transferHistory.getWhenDidItHappen());
    }
}
