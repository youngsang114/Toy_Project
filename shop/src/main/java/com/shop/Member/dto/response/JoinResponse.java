package com.shop.Member.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JoinResponse {

    private String name;
    private String email;
}
