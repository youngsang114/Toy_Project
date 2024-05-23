package com.example.restapi;

import com.example.restapi.model.UserRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestApiApplicationTests {

	@Autowired // 스프링에서 관리하는 빈들 중에서 자동으로 생성되는 오브젝트 메퍼를 가져오겠다는 의미
	private ObjectMapper objectMapper;

	@Test
	void contextLoads() throws JsonProcessingException {
		UserRequest user = new UserRequest();

		user.setUserName("홍길동");
		user.setUserAge(10);
		user.setEmail("hong@hmail.com");
		user.setIsKorean(true);

		String json = objectMapper.writeValueAsString(user);// 직렬화를 쓰기 위해서 write를 한다
		System.out.println(json);  // {"user_name":"홍길동","user_age":10,"email":"hong@hmail.com","is_korean":true}

		UserRequest dto = objectMapper.readValue(json, UserRequest.class);
		System.out.println(dto); // UserRequest(userName=홍길동, userAge=10, email=hong@hmail.com, isKorean=true)

	}

}
