package com.example.simple_board.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity,Long> {

    // select * from post where id =? and status =? order by id desc limit 1
    Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id,String status);
}
