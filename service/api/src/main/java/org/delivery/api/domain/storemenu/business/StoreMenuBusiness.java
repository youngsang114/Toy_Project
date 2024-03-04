package org.delivery.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    // register
    public StoreMenuResponse register(StoreMenuRegisterRequest request){
        // req -> entity ->save -> response
        StoreMenuEntity entity = storeMenuConverter.toEntity(request);
        StoreMenuEntity newEntity = storeMenuService.register(entity);
        StoreMenuResponse response = storeMenuConverter.toResponse(newEntity);
        return response;
    }

    // 특정 가게 검색

    public List<StoreMenuResponse> search(Long storeId){
        List<StoreMenuEntity> list = storeMenuService.getStoreMenuStoreId(storeId);

        return list.stream()
                .map(storeMenuEntity -> {
                    return storeMenuConverter.toResponse(storeMenuEntity);
                })
                .collect(Collectors.toList());
    }

}
