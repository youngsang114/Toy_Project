package com.shop.item.service;

import com.shop.item.dto.request.SaveItemDto;
import com.shop.item.entity.Item;
import com.shop.item.entity.enums.ItemSellStatus;
import com.shop.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item saveItem(SaveItemDto saveItemDto){
        Item item = Item.builder()
                .itemNm(saveItemDto.getItemNm())
                .price(saveItemDto.getPrice())
                .stockNumber(saveItemDto.getStockNumber())
                .itemDetail(saveItemDto.getItemDetail())
                .itemSellStatus(ItemSellStatus.SELL)
                .build();
        return itemRepository.save(item);

    }
}
