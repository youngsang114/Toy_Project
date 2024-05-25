package com.example.demo.user.model;

import com.example.demo.entity.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// db에 저장하므로 이름을 USerEntity로 저장
public class UserEntity extends Entity {

    private String name;
    private int score;

}
