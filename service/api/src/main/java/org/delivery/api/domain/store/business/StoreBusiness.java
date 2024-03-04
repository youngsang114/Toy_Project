package org.delivery.api.domain.store.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.store.conntroller.modeel.StoreRegisterRequest;
import org.delivery.api.domain.store.conntroller.modeel.StoreResponse;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.enums.StoreCategory;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreBusiness {
    private final StoreService storeService;
    private final StoreConverter storeConverter;
    public StoreResponse register(StoreRegisterRequest storeRegisterRequest){
        // request -> entity -> response
        StoreEntity entity = storeConverter.toEntity(storeRegisterRequest);
        StoreEntity newEntity = storeService.register(entity);
        StoreResponse response = storeConverter.toResponse(newEntity);
        return response;
    }

    public List<StoreResponse> searchCategory(StoreCategory category){
        // entity list -> response List
        List<StoreEntity> storeEntities = storeService.searchByCategory(category);
        List<StoreResponse> storeResponses = storeEntities.stream()
                .map(storeEntity -> {
                    return storeConverter.toResponse(storeEntity);
                })
                .collect(Collectors.toList());

        return storeResponses;
    }

}
