package org.delivery.api.domain.userorder.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.delivery.api.common.api.Api;
import org.delivery.api.domain.userorder.business.UserOrderBusiness;
import org.delivery.api.domain.userorder.business.model.UserOrderRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-order")
public class UserOrderApiController {

    public final UserOrderBusiness userOrderBusiness;

    // 사용자의 주문
//    @PostMapping("")
//    public Api userOrder(@Valid @RequestBody Api<UserOrderRequest> userOrderRequest){
//        userOrderBusiness.userOrder(userOrderRequest.getBody());
//    }
}
