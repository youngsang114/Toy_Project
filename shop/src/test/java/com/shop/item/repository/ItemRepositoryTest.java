package com.shop.item.repository;

import com.shop.item.entity.Item;
import com.shop.item.entity.enums.ItemSellStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createItem(){
        Item item = new Item(
                "테스트 상품",
                10000,
                100,
                "테스트 상품 상세 설명",
                ItemSellStatus.SELL,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Item save = itemRepository.save(item);
        System.out.println("save = " + save);


    }

}