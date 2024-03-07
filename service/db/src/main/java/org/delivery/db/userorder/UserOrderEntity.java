package org.delivery.db.userorder;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.user.UserEntity;
import org.delivery.db.userorder.enums.UserOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_order")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserOrderEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity; // user_table : user = n : 1

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderStatus status;

    @Column(precision = 11,scale = 4, nullable = false)
    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;

    public void orderStart(){
        this.status = UserOrderStatus.ORDER;
        this.orderedAt=LocalDateTime.now();
    }
    public void changeStatus(UserOrderStatus status){
        this.status = status;
    }
    public void orderAccept(LocalDateTime time){
        this.acceptedAt= time;
    }
    public void cookingStart(LocalDateTime time){
        this.cookingStartedAt= time;
    }
    public void deliveryStart(LocalDateTime time){
        this.deliveryStartedAt= time;
    }
    public void deliveryFinish(LocalDateTime time){
        this.receivedAt= time;
    }
}
