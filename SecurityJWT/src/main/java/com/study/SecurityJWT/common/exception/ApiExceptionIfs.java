package com.study.SecurityJWT.common.exception;


import com.study.SecurityJWT.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();

    String getErrorDescription();
}
