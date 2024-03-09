package org.delivery.db.userordermenu;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

@Entity
@Table(name = "user_order_menu")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserOrderMenuEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_order_id")
    private UserOrderEntity userOrder; // UserOrderMenu : UserOrder = n : 1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_menu_id")
    private StoreMenuEntity storeMenu;  // UserOrderMenu : StoreMenu = n : 1

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private UserOrderMenuStatus status;

    public void setStoreMenu(StoreMenuEntity storeMenu) {
        this.storeMenu = storeMenu;
    }

    public void changeMenuRegistered(){
        this.status = UserOrderMenuStatus.REGISTERED;
    }

    public void setUserOrder(UserOrderEntity userOrderEntity) {
    }

}
