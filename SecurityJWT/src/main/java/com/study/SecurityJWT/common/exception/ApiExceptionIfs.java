package com.study.SecurityJWT.common.exception;


import com.example.SpringJWT.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();

    String getErrorDescription();
}
