package org.delivery.db.userorder;

import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity,Long> {
    // 특정 유저의 모든 주문
    // select * from user_order where user_id = ? and status = ? order by id desc
    List<UserOrderEntity> findAllByUserEntityIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);



    // select * from user_order where user_id = ? and status in (?,? .. ) order by id desc
    List<UserOrderEntity> findAllByUserEntityIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);

    // 특정 주문
    // select * from user_order where id = ? and status = ? and user_id = ?
    Optional<UserOrderEntity> findAllByIdAndStatusAndUserEntityId(Long id, UserOrderStatus status, Long userId);

    Optional<UserOrderEntity> findAllByIdAndUserEntityId(Long id, Long userId);
}
