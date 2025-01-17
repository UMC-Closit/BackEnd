package UMC_7th.Closit.domain.battle.service;

import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;

public interface BattleCommandService {

    Battle createBattle(Long postId, BattleRequestDTO.CreateBattleDTO request);
}
