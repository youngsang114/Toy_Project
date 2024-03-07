package org.delivery.api.domain.userorder.business.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {

    // 주문
    // 특정 사용자가, 특정 매뉴를 주문
    // 특정 사용자 = 로그인된 세션에 들어있는 사용자
    // 특정 매뉴 id
    @NotNull
    private Long storeMenuId;


}
