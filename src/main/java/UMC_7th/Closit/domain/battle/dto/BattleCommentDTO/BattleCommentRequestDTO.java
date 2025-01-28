package UMC_7th.Closit.domain.battle.dto.BattleCommentDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class BattleCommentRequestDTO {

    @Getter
    public static class createBattleCommentRequestDTO { // 배틀 댓글 생성
        @NotNull
        private Long userId;
        @NotBlank
        private String content;
    }
}