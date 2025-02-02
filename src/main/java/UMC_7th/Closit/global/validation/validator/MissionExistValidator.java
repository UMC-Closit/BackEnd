package UMC_7th.Closit.global.validation.validator;

import UMC_7th.Closit.domain.mission.repository.MissionRepository;
import UMC_7th.Closit.global.validation.annotation.ExistMission;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMission, Long> {

    private final MissionRepository missionRepository;

    @Override
    public void initialize(ExistMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        if (missionId == null) {
            return true; // null은 검증하지 않음
        }

        boolean isValid = missionRepository.existsById(missionId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("MISSION_NOT_FOUND")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
