package com.shop.item.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaveItemResponseDto {
    private String itemNm;
    private Integer stockNumber;
}
