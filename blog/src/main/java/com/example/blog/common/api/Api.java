package com.example.blog.common.api;

import com.example.blog.common.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    public Result result;
//    @Valid
    private T body;

    public static <T> Api<T> OK(T data){
        Api<T> api = new Api<>();
        api.result=Result.OK();
        api.body=data;
        return api;
    }

    public static Api<Object> Error(Result result){
        Api<Object> api = new Api<>();
        api.result=result;
        return api;
    }

    public static Api<Object> Error(ErrorCodeIfs errorCodeIfs){
        Api<Object> api = new Api<>();
        api.result=Result.Error(errorCodeIfs);
        return api;
    }

    public static Api<Object> Error(ErrorCodeIfs errorCodeIfs,Throwable tx){
        Api<Object> api = new Api<>();
        api.result=Result.Error(errorCodeIfs, tx);
        return api;
    }
    public static Api<Object> Error(ErrorCodeIfs errorCodeIfs,String description){
        Api<Object> api = new Api<>();
        api.result=Result.Error(errorCodeIfs, description);
        return api;
    }

}
