package UMC_7th.Closit.domain.battle.dto.BattleLikeDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class BattleLikeRequestDTO {

    @Getter
    public static class CreateBattleLikeDTO { // 배틀 좋아요 생성
        @NotNull
        private Long userId;
    }
}