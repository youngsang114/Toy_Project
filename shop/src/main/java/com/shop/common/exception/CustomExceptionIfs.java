package com.shop.common.exception;


import com.shop.common.error.ErrorCodeIfs;

public interface CustomExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
