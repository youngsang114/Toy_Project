package annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import validator.PhoneNumberValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = {PhoneNumberValidator.class})
@Target({ElementType.FIELD}) // 어디에 적용시킬 건지
@Retention(RetentionPolicy.RUNTIME)  // 언제 실행시킬 건지
public @interface PhoneNumber {
    String message() default " 핸드폰 번호 양식에 맞지 않습니다 ex) 000-0000-0000";
    String regexp() default "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
