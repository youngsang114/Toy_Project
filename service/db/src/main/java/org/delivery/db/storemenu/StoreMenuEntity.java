package org.delivery.db.storemenu;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.storemenu.enums.StoreMenuStatus;
import org.delivery.db.userordermenu.UserOrderMenuEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "store_menu")
public class StoreMenuEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    StoreEntity storeEntity;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(precision = 11,scale = 4, nullable = false)
    private BigDecimal amount;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreMenuStatus status;

    @Column(length = 200, nullable = false)
    private String thumbnailUrl;
    private int likeCount;
    private int sequence;

    // 양방향 연관관계 설정
    @OneToMany(mappedBy = "storeMenu", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserOrderMenuEntity> userOrderMenuList = new ArrayList<>();

    // UserOrderMenuEntity를 storeMenu에 추가하는 메서드
    public void  addUserOrderMenu(UserOrderMenuEntity userOrderMenu) {
        userOrderMenuList.add(userOrderMenu);
        userOrderMenu.setStoreMenu(this);
    }

    public void changeStatus(StoreMenuStatus status){
        this.status = status;
    }

    public void store(StoreEntity storeEntity){
        this.storeEntity = storeEntity;
    }

    public void setStoreEntity(StoreEntity storeWithThrow) {

    }

}
