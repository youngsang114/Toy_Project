package com.study.SecurityJWT.repository;

import com.study.SecurityJWT.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Boolean existsByEmail(String email);

    UserEntity findByEmail(String email);
}
