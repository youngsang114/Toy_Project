package com.shop.item.controller;

import com.shop.common.api.Api;
import com.shop.item.dto.request.SaveItemDto;
import com.shop.item.dto.response.ItemFormDto;
import com.shop.item.dto.response.SaveItemResponseDto;
import com.shop.item.entity.Item;
import com.shop.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // admin 계정으로 아이템을 추가하는 컨트롤러
    @PostMapping("/admin/item/new")
    public Api<SaveItemResponseDto> itemNew(SaveItemDto saveItemDto){
        long id = itemService.saveItem(saveItemDto);

        SaveItemResponseDto responseDto = SaveItemResponseDto.builder()
                .itemNm(itemService.findItemById(id).get().getItemNm())
                .stockNumber(itemService.findItemById(id).get().getStockNumber())
                .build();

        return Api.OK(responseDto);
    }

    @GetMapping("/admin/item/{itemId}")
    public Api<ItemFormDto> itemDtl(@PathVariable("itemId") Long itemId){
        ItemFormDto itemFormDto = itemService.getItem(itemId);
        return Api.OK(itemFormDto);
    }

    @PostMapping("/admin/item/{itemId}")
    public Api<ItemFormDto> itemUpdate(@PathVariable("itemId") Long itemId, ItemFormDto itemFormDto){
        Item item = itemService.updateItem(itemFormDto);

        ItemFormDto updateItemFormDto = ItemFormDto.builder()
                .id(itemFormDto.getId())
                .price(item.getPrice())
                .itemDetail(item.getItemDetail())
                .itemNm(item.getItemNm())
                .stockNumber(item.getStockNumber())
                .build();
        return Api.OK(updateItemFormDto);
    }
}
