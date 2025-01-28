package UMC_7th.Closit.domain.battle.service.battleCommentService;

import UMC_7th.Closit.domain.battle.converter.BattleCommentConverter;
import UMC_7th.Closit.domain.battle.dto.battleCommentDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.BattleComment;
import UMC_7th.Closit.domain.battle.repository.BattleCommentRepository;
import UMC_7th.Closit.domain.battle.repository.BattleRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BattleCommentServiceImpl implements BattleCommentService {

    private final UserRepository userRepository;
    private final BattleRepository battleRepository;
    private final BattleCommentRepository battleCommentRepository;

    @Override
    @Transactional
    public BattleComment createBattleComment(Long battleId, BattleCommentRequestDTO.createBattleCommentRequestDTO request) { // 배틀 댓글 생성
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Battle battle = battleRepository.findById(battleId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.BATTLE_NOT_FOUND));

        BattleComment battleComment = BattleCommentConverter.toBattleComment(user, battle, request);

        return battleCommentRepository.save(battleComment);
    }
}
