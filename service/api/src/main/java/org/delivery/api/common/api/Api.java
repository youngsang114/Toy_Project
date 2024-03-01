package org.delivery.api.common.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeIfs;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data){
        Api<T> api = new Api<>();
        api.body=data;
        api.result=Result.OK();
        return api;
    }

    public static  Api<Object> ERROR(Result result){
        Api api = new Api<Object>();
        api.body=result;
        api.result=result;
        return api;
    }
    public static  Api<Object> ERROR(ErrorCodeIfs errorCodeIfs){
        Api api = new Api<Object>();
        api.result=Result.ERROR(errorCodeIfs);
        return api;
    }

    public static  Api<Object> ERROR(ErrorCodeIfs errorCodeIfs,Throwable tx){
        Api api = new Api<Object>();
        api.result=Result.ERROR(errorCodeIfs,tx);
        return api;
    }

    public static  Api<Object> ERROR(ErrorCodeIfs errorCodeIfs,String description){
        Api api = new Api<Object>();
        api.result=Result.ERROR(errorCodeIfs,description);
        return api;
    }
}
