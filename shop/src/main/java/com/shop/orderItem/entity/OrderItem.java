package com.shop.orderItem.entity;

import com.shop.item.entity.Item;
import com.shop.order.entity.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;  // 주문 가격

    private int count;  // 수량

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    // 양방향 연관관계 편의 메서드
    public void setOrder(Order order){
        this.order=order;
        order.getOrderItems().add(this);
    }
}
