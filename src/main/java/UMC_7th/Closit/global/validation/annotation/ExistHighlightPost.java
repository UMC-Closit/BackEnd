package UMC_7th.Closit.global.validation.annotation;

import UMC_7th.Closit.global.validation.validator.HighlightPostExistValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HighlightPostExistValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistHighlightPost {

    String message() default "해당하는 하이라이트 게시글이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
