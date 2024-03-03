package org.delivery.db.user.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.user.store.enums.StoreCategory;
import org.delivery.db.user.store.enums.StoreStatus;

import java.math.BigDecimal;

@Entity
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@EqualsAndHashCode(callSuper = true)
public class StoreEntity extends BaseEntity {

    @Column(length = 100,nullable = false)
    private String name;

    @Column(length = 150,nullable = false)
    private String address;

    @Column(length = 50,nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreStatus status;

    @Column(length = 50,nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreCategory category;

    private double star;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;

    @Column(precision = 11,scale = 4,nullable = false)
    private BigDecimal minimumAmount;

    @Column(precision = 11,scale = 4,nullable = false)
    private BigDecimal minimumDeliveryAmount;

    @Column(length = 20)
    private String phoneNumber;

}
