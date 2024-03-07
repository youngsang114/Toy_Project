package org.delivery.db.userorder;

import org.delivery.db.userorder.enums.UserOrderStatus;
import org.delivery.db.userordermenu.UserOrderMenuEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity,Long> {

    // 특정 유저의 모든 주문
    // select 8 from user_order where user_id =? and status =? order bt id desc;
    List<UserOrderEntity> findAllByUserEntityIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);


    // select 8 from user_order where user_id =? and status in (?,?,...) order bt id desc;
    List<UserOrderEntity> findAllByUserEntityIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);


    // 특정 주문
    // select 8 from user_order where id=? and status =? and user_id =?
    Optional<UserOrderEntity> findAllByIdAndStatusAndUserEntityId(Long id, UserOrderStatus status, Long userId);

}
