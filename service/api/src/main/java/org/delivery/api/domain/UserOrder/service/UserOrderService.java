package org.delivery.api.domain.UserOrder.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userorder.UserOrderRepository;
import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrderWithThrow(Long id, Long userId){
        return userOrderRepository.findAllByIdAndStatusAndUserEntityId(id, UserOrderStatus.REGISTERED, userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    public List<UserOrderEntity> getUserOrderList(Long userId){
        return userOrderRepository.findAllByUserEntityIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    public List<UserOrderEntity> getUserOrderList(Long userId,List<UserOrderStatus> statusList){
        return userOrderRepository.findAllByUserEntityIdAndStatusInOrderByIdDesc(userId, statusList);
    }

    // 현재 진행중인 내역
    public List<UserOrderEntity> current(Long userId){
        return getUserOrderList(userId,
                List.of(
                        UserOrderStatus.ORDER,
                        UserOrderStatus.COOKING,
                        UserOrderStatus.DELIVERY,
                        UserOrderStatus.ACCEPT
                ));
    }

    // 과거 주문한 내역
    public List<UserOrderEntity> history(Long userId){
        return getUserOrderList(userId,
                List.of(
                        UserOrderStatus.RECEIVE
                ));
    }
    

    // 주문(create)
    @Transactional(readOnly = false)
    public UserOrderEntity order(UserOrderEntity userOrderEntity){
        return Optional.ofNullable(userOrderEntity)
                .map(it -> {
                    it.orderStatus();
                    return userOrderRepository.save(it);
                } )
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
    // 상태 변경 메서드
    public UserOrderEntity setStatus(UserOrderEntity userOrderEntity, UserOrderStatus status){
        userOrderEntity.changeStatus(status);
        return userOrderEntity;
    }
    // 주문 확인
    public UserOrderEntity accept(UserOrderEntity userOrderEntity){
        userOrderEntity.acceptOrderTime(LocalDateTime.now());
        return setStatus(userOrderEntity, UserOrderStatus.ACCEPT);
    }

    // 조리 시작
    public UserOrderEntity cooking(UserOrderEntity userOrderEntity){
        userOrderEntity.cookingStart(LocalDateTime.now());
        return setStatus(userOrderEntity, UserOrderStatus.COOKING);
    }

    // 배달 시작
    public UserOrderEntity delivery(UserOrderEntity userOrderEntity){
        userOrderEntity.deliveryStart(LocalDateTime.now());
        return setStatus(userOrderEntity, UserOrderStatus.DELIVERY);
    }

    // 배달 완료

    public UserOrderEntity receive(UserOrderEntity userOrderEntity){
        userOrderEntity.deliveryFinish(LocalDateTime.now());
        return setStatus(userOrderEntity, UserOrderStatus.RECEIVE);
    }

}
