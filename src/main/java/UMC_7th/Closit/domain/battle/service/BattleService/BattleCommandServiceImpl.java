package UMC_7th.Closit.domain.battle.service.BattleService;

import UMC_7th.Closit.domain.battle.converter.BattleConverter;
import UMC_7th.Closit.domain.battle.dto.BattleDTO.BattleRequestDTO;
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
    public Battle createBattle (Long userId, BattleRequestDTO.CreateBattleDTO request) { // 배틀 생성
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Battle battle = BattleConverter.toBattle(post, request);

        // 본인의 게시글이 아닐 경우, 배틀 게시글로 업로드 불가능
        if (!post.getUser().getId().equals(userId)) {
            throw new GeneralException(ErrorStatus.POST_NOT_MINE);
        }

        return battleRepository.save(battle);
    }

    @Override
    public Battle challengeBattle (Long userId, Long battleId, BattleRequestDTO.ChallengeBattleDTO request) { // 배틀 신청
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Battle challengeBattle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        // 동일한 게시글로 배틀 불가능
        if (request.getPostId().equals(challengeBattle.getPost1().getId())) {
            throw new GeneralException(ErrorStatus.BATTLE_NOT_CHALLENGE);
        }

        // 배틀 형성 완료 -> 신청 불가능
        if (challengeBattle.getPost2() != null) {
            throw new GeneralException(ErrorStatus.BATTLE_ALREADY_EXIST);
        }

        // 내 게시글에 배틀 신청 불가능
        if (challengeBattle.getPost1().getUser().getId().equals(userId)) {
            throw new GeneralException(ErrorStatus.POST_NOT_APPLY);
        }

        // 본인의 게시글이 아닐 경우, 배틀 신청 불가능
        if (!post.getUser().getId().equals(userId)) {
            throw new GeneralException(ErrorStatus.POST_NOT_MINE);
        }

        challengeBattle.setPost2(post);

        return battleRepository.save(challengeBattle);
    }

    @Override
    public Vote voteBattle (Long userId, Long battleId, BattleRequestDTO.VoteBattleDTO request) { // 배틀 투표
        User user = userRepository.findById(userId)
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
        boolean alreadyVoted = voteRepository.existsByBattleIdAndUserId(battleId, userId);
        if (alreadyVoted) {
            throw new GeneralException(ErrorStatus.VOTE_ALREADY_EXIST);
        }

        // 마감 기한 후 투표 방지
        if (battle.availableVote()) {
            throw new GeneralException(ErrorStatus.VOTE_EXPIRED);
        }

        // 챌린지 게시글 투표 방지
        if (battle.getPost2() == null) {
            throw new GeneralException(ErrorStatus.POST_IS_CHALLENGE);
        }

        if (battle.getPost1().getId().equals(vote.getVotedPostId())) { // 첫 번째 게시글에 투표
            battle.incrementFirstVotingCnt();
        } else if (battle.getPost2().getId().equals(vote.getVotedPostId())) { // 두 번째 게시글에 투표
            battle.incrementSecondVotingCnt();
        }
        Integer firstVotingCnt = battle.getFirstVotingCnt();
        Integer secondVotingCnt = battle.getSecondVotingCnt();
        int totalVoting = firstVotingCnt + secondVotingCnt;

        // 투표 수 비율로 반환
        double firstVotingPercentage = (totalVoting == 0) ? 0.0 : (firstVotingCnt * 100.0) / totalVoting;
        double secondVotingPercentage = (totalVoting == 0) ? 0.0 : (secondVotingCnt * 100.0) / totalVoting;

        battle.setFirstVotingRate(firstVotingPercentage);
        battle.setSecondVotingRate(secondVotingPercentage);

        vote.setUser(userId);
        vote.setBattle(battle);
        vote.setVotedPostId(request.getPostId());

        return voteRepository.save(vote);
    }

    @Override
    public void deleteBattle (Long userId, Long battleId) {
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        // 배틀 게시글을 생성한 이가 아닐 경우 , 삭제 불가능
        if (!battle.getPost1().getUser().getId().equals(userId)) {
            throw new GeneralException(ErrorStatus.POST_NOT_MINE);
        }

        battleRepository.delete(battle);
    }
}
