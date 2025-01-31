package UMC_7th.Closit.domain.battle.converter;

import UMC_7th.Closit.domain.battle.dto.BattleCommentDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleCommentDTO.BattleCommentResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.BattleComment;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class BattleCommentConverter {

    public static BattleComment toBattleComment (User user, Battle battle, BattleCommentRequestDTO.createBattleCommentRequestDTO request) { // 배틀 댓글 생성
        return BattleComment.builder()
                .user(user)
                .battle(battle)
                .content(request.getContent())
                .build();
    }

    public static BattleCommentResponseDTO.createBattleCommentResultDTO createBattleCommentResponseDTO (BattleComment battleComment) {
        return BattleCommentResponseDTO.createBattleCommentResultDTO.builder()
                .battleCommentId(battleComment.getId())
                .createdAt(battleComment.getCreatedAt())
                .build();
    }

    public static BattleCommentResponseDTO.BattleCommentPreviewDTO battleCommentPreviewDTO (BattleComment battleComment) { // 배틀 댓글 조회
        return BattleCommentResponseDTO.BattleCommentPreviewDTO.builder()
                .userId(battleComment.getUser().getId())
                .battleCommentId(battleComment.getId())
                .content(battleComment.getContent())
                .createdAt(battleComment.getCreatedAt())
                .build();
    }

    public static BattleCommentResponseDTO.BattleCommentPreviewListDTO battleCommentPreviewListDTO(Slice<BattleComment> battleCommentList) {
        List<BattleCommentResponseDTO.BattleCommentPreviewDTO> battleCommentPreviewListDTO = battleCommentList.stream()
                .map(BattleCommentConverter::battleCommentPreviewDTO).collect(Collectors.toList());

        return BattleCommentResponseDTO.BattleCommentPreviewListDTO.builder()
                .battleCommentPreviewList(battleCommentPreviewListDTO)
                .listSize(battleCommentPreviewListDTO.size())
                .isFirst(battleCommentList.isFirst())
                .isLast(battleCommentList.isLast())
                .hasNext(battleCommentList.hasNext())
                .build();
    }
}