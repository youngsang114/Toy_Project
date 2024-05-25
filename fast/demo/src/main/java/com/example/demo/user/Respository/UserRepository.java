package com.example.demo.user.Respository;

import com.example.demo.user.model.UserEntity;
import db.SimpleDataRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Collectors;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    // select * from user where score >= [??];
    public List<UserEntity> findAllByScoreGreaterThanEqual(int score);

    // select * from user where score >= ?? and score <= ??
    public List<UserEntity> findAllByScoreGreaterThanEqualAndScoreLessThanEqual(int min, int max);

    // 네이티브 쿼리 활용법
    @Query(
//            value = "select u from user u where u.score >= ?1 AND u.score <= ?2",
            value = "select * from user as u where u.score >= :min AND u.score <= :max",
            nativeQuery = true
    )
    List<UserEntity> score(
            @Param(value= "min") int min,
            @Param(value = "max") int max);


}

//public class UserRepository extends SimpleDataRepository<UserEntity, Long> {
//
//    public List<UserEntity> FindAllScoreGreaterThan(int score){
//        return this.findAll()
//                .stream()
//                .filter(it -> {
//                    return it.getScore() >= score;
//                }).collect(Collectors.toList());
//    }
//}
