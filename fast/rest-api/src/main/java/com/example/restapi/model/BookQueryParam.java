package com.example.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 롬복 -> 자동으로 getter,setter 메서드 만들어짐 왼쪽에 structrue를 눌러서 확인 가능
@AllArgsConstructor
@NoArgsConstructor
public class BookQueryParam {
    private String category;
    private String issuedYear;
    private String issuedMonth;
    private String issuedDay;
}
