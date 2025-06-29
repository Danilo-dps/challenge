package user_common.domain.adapter;

import user_common.domain.model.Store;
import user_common.domain.record.StoreResponse;

public class Store2StoreResponse {

    private Store2StoreResponse() {}

    public static StoreResponse convert(Store store){
        return new StoreResponse(store.getStoreId(), store.getStoreName(), store.getStoreEmail(), store.getBalance());
    }
}
