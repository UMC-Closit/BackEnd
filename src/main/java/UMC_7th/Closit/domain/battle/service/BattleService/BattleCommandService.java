package UMC_7th.Closit.domain.battle.service.BattleService;

import UMC_7th.Closit.domain.battle.dto.BattleDTO.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.Vote;

public interface BattleCommandService {

    Battle createBattle(Long userId, BattleRequestDTO.CreateBattleDTO request); // 배틀 생성
    Battle challengeBattle(Long userId, Long battleId, BattleRequestDTO.ChallengeBattleDTO request); // 배틀 신청
    Vote voteBattle(Long userId, Long battleId, BattleRequestDTO.VoteBattleDTO request); // 배틀 투표
    void deleteBattle(Long userId, Long battleId); // 배틀 삭제
}
