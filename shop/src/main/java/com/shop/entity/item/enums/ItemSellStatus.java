package com.shop.entity.item.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ItemSellStatus {


    SELL("판매중"),
    SOLD("품절");

    private String description;
}

