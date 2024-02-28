package com.example.session.db;

import com.example.session.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRepository {

    private List<UserDto> userList = new ArrayList<>();


    public Optional<UserDto> findByName(String name){
        return userList.stream().filter(it -> it.getName().equals(name)).findFirst();
    }

    @PostConstruct
    public void init(){

        userList.add(new UserDto("홍길동","1234"));
        userList.add(new UserDto("유관순","1234"));
        userList.add(new UserDto("김철수","1234"));
    }
}
