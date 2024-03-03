package org.delivery.api.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Authorization Interceptor url : {}",request.getRequestURI());

        // WEB, chrome의 경우 GET, POST OPTION 이라는 api를 요청해서, 해당 메서드를 지원하는지 체크하는 api -> pass
        if (HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }

        // js, htm, png같은 resources를 요청하는 경우 -> pass
        if (handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // TODO header 검증
        String accessToken = request.getHeader("authorization-token");
        if (accessToken == null){
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        Long userId = tokenBusiness.validationAccessToken(accessToken);

        if (userId !=null){
            RequestAttributes requestAttributes = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestAttributes.setAttribute("userId", userId,RequestAttributes.SCOPE_REQUEST);
            return true;
        }
        throw new ApiException(ErrorCode.SERVER_ERROR,"인증실패");
    }
}
