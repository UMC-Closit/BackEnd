package UMC_7th.Closit.domain.battle.service;

import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.repository.BattleRepository;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BattleQueryServiceImpl implements BattleQueryService {

    private final BattleRepository battleRepository;
    private final PostRepository postRepository;

    @Override
    public Optional<Battle> findBattle(Long battleId) { // 배틀 게시글 목록 조회
        return battleRepository.findById(battleId);
    }

    @Override
    public Slice<Battle> getBattleList(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        Slice<Battle> battleList = battleRepository.findAll(pageable);
        return battleList;
    }

    @Override
    public Optional<Battle> findChallengeBattle(Long battleId) { // 배틀 챌린지 게시글 목록 조회

        return battleRepository.findById(battleId);
    }

    @Override
    public Slice<Battle> getChallengeBattleList(Integer page) {
        Pageable pageable = PageRequest.of(page, 10);

        // secondPostId가 null 인 것을 기준으로 조회
        Slice<Battle> challengeBattleList = battleRepository.findByPost2IsNull(pageable);

        return challengeBattleList;
    }
}