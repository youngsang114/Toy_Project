package com.example.SpringJWT.common.api;

import com.example.SpringJWT.common.error.ErrorCode;
import com.example.SpringJWT.common.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {

    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    public static Result OK(){
        return Result.builder()
                .resultCode(ErrorCode.OK.getHttpStatusCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공입니다")
                .build();
    }
    public static Result OK_DELETE(){
        return Result.builder()
                .resultCode(ErrorCode.OK.getHttpStatusCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("삭제 성공입니다")
                .build();
    }

    public static Result Error(ErrorCodeIfs errorCodeIfs){
        return Result.builder()
                .resultCode(errorCodeIfs.getHttpStatusCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription("에러가 발생하였습니다")
                .build();
    }

    public static Result Error(ErrorCodeIfs errorCodeIfs, Throwable tx){
        return Result.builder()
                .resultCode(errorCodeIfs.getHttpStatusCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(tx.getLocalizedMessage())
                .build();
    }
    public static Result Error(ErrorCodeIfs errorCodeIfs,String description){
        return Result.builder()
                .resultCode(errorCodeIfs.getHttpStatusCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(description)
                .build();
    }



}
