package UMC_7th.Closit.domain.battle.service.BattleService;

import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.repository.BattleRepository;
import UMC_7th.Closit.domain.battle.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BattleQueryServiceImpl implements BattleQueryService {

    private final BattleRepository battleRepository;
    private final VoteRepository voteRepository;

    @Override
    public Slice<Battle> getBattleList(Long userId, Integer page) { // 배틀 게시글 목록 조회
        Pageable pageable = PageRequest.of(page, 10);

        // secondPostId가 not null 인 것을 기준으로 조회
        Slice<Battle> battleList = battleRepository.findByPost2IsNotNull(pageable);

        // 투표하지 않았으면 해당 배틀 투표 수 null로 표시
        battleList.forEach(battle -> {
            boolean isVoted = voteRepository.existsByBattleIdAndUserId(battle.getId(), userId);
            if (!isVoted) {
                battle.setFirstVotingCnt(null);
                battle.setSecondVotingCnt(null);
            }
        });

        return battleList;
    }

    @Override
    public Slice<Battle> getChallengeBattleList(Integer page) { // 배틀 챌린지 게시글 목록 조회
        Pageable pageable = PageRequest.of(page, 10);

        // secondPostId가 null 인 것을 기준으로 조회
        Slice<Battle> challengeBattleList = battleRepository.findByPost2IsNull(pageable);

        return challengeBattleList;
    }
}