package UMC_7th.Closit.domain.battle.service;

import UMC_7th.Closit.domain.battle.converter.BattleConverter;
import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.Vote;
import UMC_7th.Closit.domain.battle.repository.BattleRepository;
import UMC_7th.Closit.domain.battle.repository.VoteRepository;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
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
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    @Override
    public Battle createBattle (BattleRequestDTO.CreateBattleDTO request) { // 배틀 생성
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Battle battle = BattleConverter.toBattle(post, request);

        // 중복 배틀 생성 방지
        if (post.isBattle()) {
            throw new GeneralException(ErrorStatus.POST_ALREADY_BATTLE);
        }

        post.isBattle(true);

        return battleRepository.save(battle);
    }

    @Override
    public Battle challengeBattle (Long battleId, BattleRequestDTO.ChallengeBattleDTO request) { // 배틀 신청
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Battle challengeBattle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        if (request.getPostId().equals(challengeBattle.getPost1().getId())) { // 동일한 게시글로 배틀 불가능
            throw new GeneralException(ErrorStatus.BATTLE_NOT_CHALLENGE);
        }

        if (challengeBattle.getPost2() != null) { // 배틀 형성 완료 -> 신청 불가능
            throw new GeneralException(ErrorStatus.BATTLE_ALREADY_EXIST);
        }

        challengeBattle.setPost2(post);
        return battleRepository.save(challengeBattle);
    }

    @Override
    public Vote voteBattle (Long battleId, BattleRequestDTO.VoteBattleDTO request) { // 배틀 투표
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Vote vote = BattleConverter.toVote(user, request);

        // 배틀이 아닌 게시글에 투표 불가능
        if (!battle.getPost1().getId().equals(request.getPostId()) && !battle.getPost2().getId().equals(request.getPostId())) {
            throw new GeneralException(ErrorStatus.POST_NOT_BATTLE);
        }

        // 이미 투표한 곳에 중복 투표 방지
        boolean alreadyVoted = voteRepository.existsByBattleId(battleId);
        if (alreadyVoted) {
            throw new GeneralException(ErrorStatus.VOTE_ALREADY_EXIST);
        }

        post.incrementVotingCount();

        vote.setUser(request.getUserId());
        vote.setBattle(battle);
        vote.setVotedPostId(request.getPostId());

        return voteRepository.save(vote);
    }

    @Override
    public void deleteBattle (Long battleId) {
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        battleRepository.delete(battle);
    }
}
