package com.study.SecurityJWT.repository;

import com.study.SecurityJWT.entity.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RefreshRepository extends JpaRepository<RefreshEntity,Long> {

    Boolean existsByRefresh(String refresh);

    // 과거의 refresh token을 삭제하기 위한 메서드
    @Transactional
    void deleteByRefresh(String refresh);
}
