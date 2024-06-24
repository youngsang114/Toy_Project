package com.example.filter_interceptor_aop.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 진입 전
        log.info(">>>>> 진입");

//        HttpServletRequestWrapper req = new HttpServletRequestWrapper((HttpServletRequest) request);
//        HttpServletResponseWrapper res = new HttpServletResponseWrapper((HttpServletResponse) response);

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

//        BufferedReader br = req.getReader();
//        List<String> list = br.lines().collect(Collectors.toList());
//
//        list.forEach(it ->{
//            log.info("{}",it);
//        });
        // HttpServletRequestWrapper의 reader는 inputStream 이기 때문에, 즉 controller에서, body의 내용을 읽을 수 없는 오류가 난다

        chain.doFilter(req,res);

        String reqJson = new String(req.getContentAsByteArray());
        log.info("req : {}",reqJson);
        String resJson = new String(res.getContentAsByteArray());
        log.info("res : {}",resJson);

        log.info("<<<<< 리턴");
        // 진입 후

        res.copyBodyToResponse(); // stream 한번 읽었지만, copy해놓은 내용을 다시 res에 덮어 씌우는 메서드 -> controller에 body가 들어가게 된다
    }
}
