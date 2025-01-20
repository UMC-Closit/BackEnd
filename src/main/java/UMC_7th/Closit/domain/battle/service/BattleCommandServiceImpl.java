package UMC_7th.Closit.domain.battle.service;

import UMC_7th.Closit.domain.battle.converter.BattleConverter;
import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.Vote;
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
    public Battle createBattle (BattleRequestDTO.CreateBattleDTO request) { // 배틀 생성
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Battle battle = BattleConverter.toBattle(post, request);

        return battleRepository.save(battle);
    }

    @Override
    public Battle challengeBattle (Long battleId, BattleRequestDTO.ChallengeBattleDTO request) { // 배틀 신청
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Battle challengeBattle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND)); // 배틀 존재 X -> BATTLE_NOT_FOUND

        if (challengeBattle.getPost2() != null) { // 배틀 형성 완료 -> 신청 불가능
            throw new GeneralException(ErrorStatus.BATTLE_ALERADY_EXIST);
        }

        challengeBattle.setPost2(post);
        return battleRepository.save(challengeBattle);
    }

    @Override
    public Vote voteBattle (Long battleId, BattleRequestDTO.VoteBattleDTO request) { // 배틀 투표
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));


        return null;
    }
}
