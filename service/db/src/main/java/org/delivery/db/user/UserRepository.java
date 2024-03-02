package org.delivery.db.user;

import org.delivery.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    // select * from user where id =? and status =? order by id DESC limit 1;
    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long userId, UserStatus userStatus);

    // select * from user where email =? and password =? and status =? order bt id desc limit;
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus userStatus);
}
