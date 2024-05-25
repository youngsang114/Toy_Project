package com.example.demo.user.service;

import com.example.demo.user.Respository.UserRepository;
import com.example.demo.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public UserEntity save(UserEntity userEntity) {
        //save
        return userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public void delete(UserEntity id) {
        userRepository.delete(id);
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> filterScore(int score){
//        return null;
        return userRepository.findAllByScoreGreaterThanEqual(score);
    }
    public List<UserEntity> filterScore(int min,int max){
//        return null;
//        return userRepository.findAllByScoreGreaterThanEqualAndScoreLessThanEqual(min, max);
        return userRepository.score(min, max);
    }
}
