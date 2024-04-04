package com.shop.item.service;

import com.shop.common.error.ItemError;
import com.shop.common.exception.CustomException;
import com.shop.item.dto.request.SaveItemDto;
import com.shop.item.dto.response.ItemFormDto;
import com.shop.item.entity.Item;
import com.shop.item.entity.enums.ItemSellStatus;
import com.shop.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public long saveItem(SaveItemDto saveItemDto){
        Item item = Item.builder()
                .itemNm(saveItemDto.getItemNm())
                .price(saveItemDto.getPrice())
                .stockNumber(saveItemDto.getStockNumber())
                .itemDetail(saveItemDto.getItemDetail())
                .itemSellStatus(ItemSellStatus.SELL)
                .build();
        return itemRepository.save(item).getId();

    }
    public Optional<Item> findItemById(Long id){
        return itemRepository.findById(id);
    }

    public ItemFormDto getItem(Long itemId){
        Item item = itemRepository.findById(itemId).orElseThrow(()-> new CustomException(ItemError.NO_ITEM));
        return ItemFormDto.builder()
                .id(itemId)
                .itemNm(item.getItemNm())
                .price(item.getPrice())
                .itemDetail(item.getItemDetail())
                .stockNumber(item.getStockNumber())
                .build();
    }

    public Item updateItem(ItemFormDto itemFormDto){
        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(() -> new CustomException(ItemError.NO_ITEM));
        item.updateItem(itemFormDto);
        return item;
    }
}
