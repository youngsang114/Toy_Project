package com.example.SpringJWT.common.error;

public interface ErrorCodeIfs {
    Integer getHttpStatusCode();  // 실제 httpStatusCode
    Integer getErrorCode();  // 이번 프로젝트에서 사용할 code
    String getDescription(); // 설명
}
