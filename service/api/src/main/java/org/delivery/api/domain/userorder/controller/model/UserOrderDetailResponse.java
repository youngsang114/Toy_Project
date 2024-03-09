package org.delivery.api.domain.userorder.controller.model;

import lombok.*;
import org.delivery.api.domain.store.conntroller.modeel.StoreResponse;
import org.delivery.api.domain.storemenu.controller.model.StoreMenuResponse;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDetailResponse {

    // 사용자에 대한 주문건
    private UserOrderResponse userOrderResponse;
    // 가게가 어딘지
    private StoreResponse storeResponse;
    // 어떠한 매뉴를 주문했는지
    private List<StoreMenuResponse> storeMenuResponseList;

}
