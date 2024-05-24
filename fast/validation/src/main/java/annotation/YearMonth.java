package annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import validator.YearMonthValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = {YearMonthValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotBlank
public @interface YearMonth {
    String message() default "날짜 양식에 맞지 않습니다 ex) 202405";

    String pattern() default "yyyyMM";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
