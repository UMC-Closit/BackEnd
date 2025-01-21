package UMC_7th.Closit.domain.battle.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class BattleRequestDTO {

    @Getter
    public static class CreateBattleDTO { // 배틀 생성
        @NotNull
        private Long postId;
        @NotBlank
        private String title;
        @DateTimeFormat(pattern = "yyMMdd")
        private LocalDate deadline;
    }

    @Getter
    public static class ChallengeBattleDTO { // 배틀 신청
        @NotNull
        private Long postId;
    }

    @Getter
    public static class VoteBattleDTO { // 배틀 투표
        @NotNull
        private Long postId;
    }
}
