package user_common.domain.adapter;

import user_common.domain.dto.StoreDTO;
import user_common.domain.model.Store;

public class Store2StoreDTO {

    private Store2StoreDTO() {}

    public static StoreDTO convert(Store store){
        return new StoreDTO(store.getStoreId(),  store.getStoreName(), store.getCnpj(), store.getStoreEmail(), store.getBalance());
    }
}
