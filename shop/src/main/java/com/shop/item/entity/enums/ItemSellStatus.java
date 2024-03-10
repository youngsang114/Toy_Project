package com.shop.item.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ItemSellStatus {


    SELL("판매중"),
    SOLD("품절");

    private String description;
}

