package org.delivery.api.domain.storemenu.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.storemenu.StoreMenuRepository;
import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    public StoreMenuEntity getStoreMenuWithThrow(Long id){
        Optional<StoreMenuEntity> entity = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERED);
        return entity.orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<StoreMenuEntity> getStoreMenuStoreId(Long storeId){
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId,StoreMenuStatus.REGISTERED);
    }

    public StoreMenuEntity register(StoreMenuEntity storeMenuEntity){
        return Optional.ofNullable(storeMenuEntity)
                .map((it)->{
                    it.changeStatus(StoreMenuStatus.REGISTERED);
                    return storeMenuRepository.save(it);
                }).orElseThrow(()-> new ApiException(ErrorCode.NULL_POINT));
    }
}
