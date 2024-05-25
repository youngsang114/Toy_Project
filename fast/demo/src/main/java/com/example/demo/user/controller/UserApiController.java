package com.example.demo.user.controller;

import com.example.demo.user.model.UserEntity;
import com.example.demo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService; // userSerive를 스프링으로부터 주입 받을 수 있다
    @PutMapping("") // update or create
    public UserEntity create(
            @RequestBody UserEntity userEntity
    ){
        return userService.save(userEntity);
    }

    @GetMapping("/all")
    public List<UserEntity> findAll(){
        return userService.findAll();
    }

    @DeleteMapping("/id/{id}")
    public void delete(
            @PathVariable("id") Long id
    ) {
//        userService.delete(id);
    }

    @GetMapping("/id/{id}")
    public UserEntity findOne(
            @PathVariable("id") Long id
    ) {
        Optional<UserEntity> response = userService.findById(id);
        return response.orElseThrow(
                ()-> new RuntimeException("찾는 id의 회원이 없습니다")
        );
    }

    @GetMapping("/score")
    public List<UserEntity> filterScore(
            @RequestParam("score") int score
    ){
        return userService.filterScore(score);
    }
    @GetMapping("/min_max")
    public List<UserEntity> filterScore(
            @RequestParam("min") int min,
            @RequestParam("max") int max

    ){
        return userService.filterScore(min,max);
    }

}
