package com.shop.common.error;

public interface ErrorCodeIfs {
    Integer getHttpStatusCode();  // 실제 httpStatusCode
    String getDescription(); // 설명
}
