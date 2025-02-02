package UMC_7th.Closit.global.validation.validator;

import UMC_7th.Closit.domain.highlight.repository.HighlightPostRepository;
import UMC_7th.Closit.global.validation.annotation.ExistHighlightPost;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HighlightPostExistValidator implements ConstraintValidator<ExistHighlightPost, Long> {

    private final HighlightPostRepository highlightPostRepository;

    @Override
    public void initialize(ExistHighlightPost constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long highlightPostId, ConstraintValidatorContext context) {
        if (highlightPostId == null) {
            return true; // null은 검증하지 않음
        }

        boolean isValid = highlightPostRepository.existsById(highlightPostId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("HIGHLIGHT_POST_NOT_FOUND")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
