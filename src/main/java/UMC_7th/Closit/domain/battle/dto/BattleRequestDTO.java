package UMC_7th.Closit.domain.battle.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BattleRequestDTO {


    @Getter
    public static class CreateBattleDTO { // 배틀 생성
        @NotBlank
        private String title;
        @DateTimeFormat(pattern = "yyMMdd")
        private LocalDate deadline;
    }
}
