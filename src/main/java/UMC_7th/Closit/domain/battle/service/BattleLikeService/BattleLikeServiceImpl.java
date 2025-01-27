package UMC_7th.Closit.domain.battle.service.BattleLikeService;

import UMC_7th.Closit.domain.battle.converter.BattleLikeConverter;
import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeRequestDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.BattleLike;
import UMC_7th.Closit.domain.battle.repository.BattleLikeRepository;
import UMC_7th.Closit.domain.battle.repository.BattleRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BattleLikeServiceImpl implements BattleLikeService{

    private final UserRepository userRepository;
    private final BattleRepository battleRepository;
    private final BattleLikeRepository battleLikeRepository;

    @Override
    @Transactional
    public BattleLike createBattleLike (Long battleId, BattleLikeRequestDTO.CreateBattleLikeDTO request) { // 배틀 좋아요 생성
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        BattleLike battleLike = BattleLikeConverter.toBattleLike(user, battle);

        // 배틀 좋아요 중복 생성 방지
        boolean battleLikeExist = battleLikeRepository.existsBattleLikeByBattleIdAndUserId(battleId, request.getUserId());
        if (battleLikeExist) {
            throw new GeneralException(ErrorStatus.BATTLE_LIKES_ALREADY_EXIST);
        }

        battle.increaseLikeCount();

        return battleLikeRepository.save(battleLike);
    }

    @Override
    @Transactional(readOnly = true)
    public Slice<BattleLike> getBattleLikeList(Long battleId, Integer page) { // 배틀 좋아요 조회
        Pageable pageable = PageRequest.of(page, 10);

        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        Slice<BattleLike> battleLikeList = battleLikeRepository.findAllByBattleId(battleId, pageable);

        return battleLikeList;
    }

    @Override
    @Transactional
    public void deleteBattleLike (Long battleId, Long battleLikeId) {
        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        BattleLike battleLike = battleLikeRepository.findById(battleLikeId)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_LIKES_NOT_FOUND));

        battle.decreaseLikeCount();

        battleLikeRepository.delete(battleLike);
    }
}