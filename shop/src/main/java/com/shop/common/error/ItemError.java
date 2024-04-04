package com.shop.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemError implements ErrorCodeIfs{

    NO_ITEM(400,"no item... find others");

    private final Integer httpStatusCode;
    private final String description;
}
