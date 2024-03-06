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

    private LocalDateTime orderAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStaredAt;

    private LocalDateTime receivedAt;
}
