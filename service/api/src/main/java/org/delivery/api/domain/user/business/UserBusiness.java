package org.delivery.api.domain.user.business;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.swing.text.html.parser.Entity;
import java.util.Objects;
import java.util.Optional;

@Business
@RequiredArgsConstructor
public class UserBusiness {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;

    /**
     * 사용자에 대한 가입처리 로직
     * 1. request -> entity
     * 2. entity -> save
     * 3. save Entity -> response
     * 4. response return
     */
    public UserResponse register(UserRegisterRequest request) {

        /*UserEntity entity = userConverter.toEntity(request);
        UserEntity newEntity = userService.register(entity);
        UserResponse response = userConverter.toResponse(newEntity);
        return response;*/

        return Optional.ofNullable(request)
                .map(it->userConverter.toEntity(it))
                .map(it->userService.register(it))
                .map(it->userConverter.toResponse(it))
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "request null"));

    }

    /**
     * 1. email, password를 가지고 사용자 체크
     * 2. user entity 로그인 확인
     * 3. token 생성
     * 4. token response
     */
    @Transactional
    public TokenResponse login(UserLoginRequest request) {
        UserEntity userEntity = userService.login(request.getEmail(), request.getPassword());

        TokenResponse tokenResponse = tokenBusiness.issueToken(userEntity);

        return tokenResponse;
    }

    public UserResponse me(Long userId) {

        UserEntity userEntity = userService.getUserWithThrow(userId);
        UserResponse response = userConverter.toResponse(userEntity);
        return response;
    }
}
