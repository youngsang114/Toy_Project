package com.example.filter_interceptor_aop.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class OpenApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // true -> controller로 전달, flase 전달하지 않는다
        log.info("pre handler");

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        OpenApi methodLevel = handlerMethod.getMethodAnnotation(OpenApi.class);
        if (methodLevel !=null){
            log.info("method level");
            return true;
        }

        OpenApi classLevel = handlerMethod.getBeanType().getAnnotation(OpenApi.class);
        if (classLevel != null){
            log.info("class level");
            return true;
        }
        log.info("open api 아닙니다 : {}", request.getRequestURI());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.info("post handler");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        log.info("after completion");
    }

}
