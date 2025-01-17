package UMC_7th.Closit.domain.battle.service;

import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;

public interface BattleCommandService {

    Battle createBattle(BattleRequestDTO.CreateBattleDTO request); // 배틀 생성
    Battle challengeBattle(Long battleId, BattleRequestDTO.ChallengeBattleDTO request); // 배틀 신청
}
