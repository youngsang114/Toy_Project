package org.delivery.api.domain.userorder.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.store.converter.StoreConverter;
import org.delivery.api.domain.store.service.StoreService;
import org.delivery.api.domain.storemenu.converter.StoreMenuConverter;
import org.delivery.api.domain.storemenu.service.StoreMenuService;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.api.domain.userorder.controller.model.UserOrderDetailResponse;
import org.delivery.api.domain.userorder.controller.model.UserOrderRequest;
import org.delivery.api.domain.userorder.controller.model.UserOrderResponse;
import org.delivery.api.domain.userorder.converter.UserOrderConverter;
import org.delivery.api.domain.userorder.service.UserOrderService;
import org.delivery.api.domain.userordermenu.converter.UserOrderMenuConverter;
import org.delivery.api.domain.userordermenu.service.UserOrderMenuService;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.user.UserEntity;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.hibernate.Hibernate;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Business
@RequiredArgsConstructor
public class UserOrderBusiness {

    private final UserOrderService userOrderService;
    private final UserOrderConverter userOrderConverter;
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    private final UserService userService;

    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;

    private final StoreService storeService;
    private final StoreConverter storeConverter;

    // 1. 사용자, 매뉴 id를 받는다
    // 2. userOrder 생성
    // 3. userOrderMenu 생성
    // 4. 응답 생성
    @Transactional
    public UserOrderResponse userOrder(User user, UserOrderRequest body) {
        List<StoreMenuEntity> storeMenuEntityList = body.getStoreMenuIdList()
                .stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it))
                .collect(Collectors.toList());

        UserEntity userEntity = userService.getUserWithThrow(user.getId());
        Long orderId = userEntity.getId();

        // 주문
        UserOrderEntity userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList, userEntity);

        // 양방향 관계 설정
        List<UserOrderMenuEntity> userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> {
                    UserOrderMenuEntity userOrderMenuEntity = userOrderMenuConverter.toEntity(userOrderEntity, it);
                    // StoreMenuEntity에 대한 양방향 설정
                    it.addUserOrderMenu(userOrderMenuEntity);


                    return userOrderMenuEntity;
                }).collect(Collectors.toList());

        // user_order 테이블에 저장
        UserOrderEntity newUserOrderEntity = userOrderService.order(userOrderEntity);

        // user_order_menu 테이블에 저장
        userOrderMenuEntityList.forEach(userOrderMenuEntity -> {
            userOrderMenuService.order(userOrderMenuEntity);
        });

        // response
        return userOrderConverter.toResponse(newUserOrderEntity, orderId);
    }


    @Transactional
    public List<UserOrderDetailResponse> current(User user) {
        List<UserOrderEntity> userOrderEntityList = userOrderService.current(user.getId());
        // 주문 1건씩 처리
        List<UserOrderDetailResponse> userOrderDetailResponseList = userOrderEntityList.stream()
                .map(it -> {
                    // 사용자가 주문한 매뉴
//                    List<UserOrderMenuEntity> userOrderMenuList = userOrderMenuService.getUserOrderMenu(it.getId());
                    List<UserOrderMenuEntity> userOrderMenuList = it.getUserOrderMenuList(); // 양방향 관계로 변경된 부분


                    List<StoreMenuEntity> storeMenuEntityList = userOrderMenuList.stream()
                            .map(userOrderMenuEntity -> {
                                // getId()를 호출하여 프록시를 초기화
                                Long storeId = userOrderMenuEntity.getStoreMenu().getStoreEntity().getId();
                                StoreMenuEntity storeMenuEntity = storeMenuService.getStoreMenuWithFetchJoin(userOrderMenuEntity.getStoreMenu().getId());

                                return storeMenuEntity;
                            })
                            .collect(Collectors.toList());



                    // 사용자가 주문한 스토어 TODO 리팩터링 필요
//                    StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreEntity().getId());

                    return UserOrderDetailResponse.builder()
                            .userOrderResponse(userOrderConverter.toResponse(it, user.getId()))
                            .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
//                            .storeResponse(storeConverter.toResponse(storeEntity))
                            .build()
                            ;
                }).collect(Collectors.toList());

        return userOrderDetailResponseList;
    }



    public List<UserOrderDetailResponse> history(User user) {
        List<UserOrderEntity> userOrderEntityList = userOrderService.history(user.getId());
        // 주문 1건씩 처리
        List<UserOrderDetailResponse> userOrderDetailResponseList = userOrderEntityList.stream()
                .map(it -> {
                    // 사용자가 주문한 매뉴
                    List<UserOrderMenuEntity> userOrderMenuList = userOrderMenuService.getUserOrderMenu(it.getId());


                    List<StoreMenuEntity> storeMenuEntityList = userOrderMenuList.stream()
                            .map(userOrderMenuEntity -> {
                                StoreMenuEntity storeMenuEntity = storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenu().getId());
                                return storeMenuEntity;
                            })
                            .collect(Collectors.toList());

                    // 사용자가 주문한 스토어 TODO 리팩터링 필요
                    StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreEntity().getId());

                    return UserOrderDetailResponse.builder()
                            .userOrderResponse(userOrderConverter.toResponse(it, user.getId()))
                            .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
//                            .storeResponse(storeConverter.toResponse(storeEntity))
                            .build()
                            ;
                }).collect(Collectors.toList());
        return userOrderDetailResponseList;
    }

    public UserOrderDetailResponse read(User user, Long orderId) {

        // 사용자가 주문한 매뉴 찾기
        UserOrderEntity userOrderEntity = userOrderService.getUserOrderWithThrow(orderId, user.getId());

        // 사용자가 주문한 매뉴
        List<UserOrderMenuEntity> userOrderMenuList = userOrderMenuService.getUserOrderMenu(userOrderEntity.getId());
        List<StoreMenuEntity> storeMenuEntityList = userOrderMenuList.stream()
                .map(userOrderMenuEntity -> {
                    StoreMenuEntity storeMenuEntity = storeMenuService.getStoreMenuWithThrow(userOrderMenuEntity.getStoreMenu().getId());
                    return storeMenuEntity;
                })
                .collect(Collectors.toList());

        // 사용자가 주문한 가게(스토어) TODO 리팩터링 필요
        StoreEntity storeEntity = storeService.getStoreWithThrow(storeMenuEntityList.stream().findFirst().get().getStoreEntity().getId());

        return UserOrderDetailResponse.builder()
                .userOrderResponse(userOrderConverter.toResponse(userOrderEntity, user.getId()))
                .storeMenuResponseList(storeMenuConverter.toResponse(storeMenuEntityList))
//                .storeResponse(storeConverter.toResponse(storeEntity))
                .build()
                ;

    }
}
