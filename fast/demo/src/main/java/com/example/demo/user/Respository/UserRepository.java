package com.example.demo.user.Respository;

import com.example.demo.user.model.UserEntity;
import db.SimpleDataRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepository extends SimpleDataRepository<UserEntity, Long> {

    public List<UserEntity> FindAllScoreGreaterThan(int score){
        return this.findAll()
                .stream()
                .filter(it -> {
                    return it.getScore() >= score;
                }).collect(Collectors.toList());
    }
}
