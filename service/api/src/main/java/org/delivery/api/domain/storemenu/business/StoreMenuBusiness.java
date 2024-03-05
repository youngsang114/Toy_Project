package org.delivery.api.domain.storemenu.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class StoreMenuBusiness {

    public final StoreService storeService;

    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    // register
    @Transactional
    public StoreMenuResponse register(StoreMenuRegisterRequest request){

        StoreEntity store = storeService.findById(request.getStoreId())
                .orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
        // req -> entity ->save -> response
        StoreMenuEntity entity = storeMenuConverter.toEntity(request,store);
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
