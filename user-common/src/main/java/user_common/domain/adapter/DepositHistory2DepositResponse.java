package user_common.domain.adapter;

import user_common.domain.model.DepositHistory;
import user_common.domain.record.DepositResponse;

import java.util.List;
import java.util.stream.Collectors;

public class DepositHistory2DepositResponse {

    private DepositHistory2DepositResponse(){}

    public static DepositResponse convert(DepositHistory depositHistory){
        return new DepositResponse(depositHistory.getDepositId(), depositHistory.getUser().getFullName(), depositHistory.getAmount(), depositHistory.getWhenDidItHappen());
    }

    public static List<DepositResponse> convertToList(List<DepositHistory> listDepositHistory){
        return listDepositHistory.stream()
                .map(DepositHistory2DepositResponse::convert)
                .collect(Collectors.toList());
    }
}
