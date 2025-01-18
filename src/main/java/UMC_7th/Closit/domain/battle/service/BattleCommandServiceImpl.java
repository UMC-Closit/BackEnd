package UMC_7th.Closit.domain.battle.service;

import UMC_7th.Closit.domain.battle.converter.BattleConverter;
import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.repository.BattleRepository;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BattleCommandServiceImpl implements BattleCommandService {

    private final BattleRepository battleRepository;
    private final PostRepository postRepository;

    @Override
    public Battle createBattle (BattleRequestDTO.CreateBattleDTO request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Battle battle = BattleConverter.toBattle(post, request);

        return battleRepository.save(battle);
    }

    @Override
    public Battle challengeBattle (Long battleId, BattleRequestDTO.ChallengeBattleDTO request) {
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Battle challengeBattle = battleRepository.findById(battleId)
                .map(existingBattle -> BattleConverter.toChallengeBattle(post, request)) // 배틀 존재 -> 반환
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND)); // 배틀 존재 X -> BATTLE_NOT_FOUND

        return battleRepository.save(challengeBattle);
    }
}
