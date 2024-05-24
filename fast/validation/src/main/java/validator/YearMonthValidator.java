package validator;

import annotation.YearMonth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Slf4j
public class YearMonthValidator implements ConstraintValidator<YearMonth,String> {
    public String pattern;
    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // "202405"
        // [yyyy mm] dd
        String reValue = value + "01";
        String rePattern = pattern + "dd";
        try {
            LocalDate date = LocalDate.parse(reValue, DateTimeFormatter.ofPattern(rePattern));
            log.info("yyyyMM : {}",date);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
