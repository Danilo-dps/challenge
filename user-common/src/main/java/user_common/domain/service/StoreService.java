package user_common.domain.service;

import user_common.domain.dto.StoreDTO;
import user_common.domain.record.StoreResponse;
import user_common.domain.record.TransferResponse;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    StoreDTO create(StoreDTO storeDTO);
    StoreResponse getById(UUID storeId);
    StoreResponse getByEmail(String storeEmail);
    StoreDTO update(UUID storeId, StoreResponse storeResponse);
    void delete(UUID storeId);
    List<TransferResponse> getAllTransfers(UUID storeId);
}
