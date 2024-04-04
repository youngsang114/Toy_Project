package com.shop.item.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemFormDto {

    private Long id;
    private String itemNm;
    private Integer price;
    private String itemDetail;
    private Integer stockNumber;
}
