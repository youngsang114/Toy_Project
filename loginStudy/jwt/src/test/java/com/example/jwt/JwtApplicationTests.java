package com.example.jwt;

import com.example.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;

@SpringBootTest
class JwtApplicationTests {
	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}
	@Test
	void tokenCreate(){
		HashMap<String, Object> claims = new HashMap<>();
		claims.put("user_id",923);

		LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(10);

		String jwtToken = jwtService.create(claims, expiredAt);
		System.out.println("jwtToken = " + jwtToken);

	}
	@Test
	void  tokenCreated(){
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo5MjMsImV4cCI6MTcwOTEyMTIzN30.7a25t16zha9Vhxvw5Weq9QnfkDeHWEGe6EsLyefakQE";

		jwtService.validation(token);
	}

}
