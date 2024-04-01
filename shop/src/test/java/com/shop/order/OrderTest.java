package com.shop.order;

import com.shop.item.entity.Item;
import com.shop.item.entity.enums.ItemSellStatus;
import com.shop.item.repository.ItemRepository;
import com.shop.order.entity.Order;
import com.shop.order.repository.OrderRepository;
import com.shop.orderItem.entity.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.yml")
public class OrderTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("영속성 전이 테스트")
    public void cascadeTest(){
        Order order = new Order();

        for (int i=0; i<3;i++) {
            Item item = Item.builder()
                    .itemNm("테스트상품")
                    .price(1000)
                    .itemDetail("상세설명")
                    .itemSellStatus(ItemSellStatus.SELL)
                    .stockNumber(100)
                    .build();
            itemRepository.save(item);
            OrderItem orderItem = OrderItem.builder()
                    .count(10)
                    .orderPrice(1000)
                    .item(item)
                    .build();
            orderItem.setOrder(order);

        }
        orderRepository.saveAndFlush(order);
        em.clear();

            Order saveOrder = orderRepository.findById(order.getId())
                    .orElseThrow(() -> new EntityNotFoundException());

            Assertions.assertEquals(3,saveOrder.getOrderItems().size());

    }
}
