package UMC_7th.Closit.domain.battle.converter;

import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.post.entity.Post;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BattleConverter {

    public static Battle toBattle (Post post, BattleRequestDTO.CreateBattleDTO request) { // 배틀 생성
        return Battle.builder()
                .id(post.getId())
                .title(request.getTitle())
                .deadline(request.getDeadline())
                .build();
    }
    public static BattleResponseDTO.CreateBattleResultDTO createBattleResultDTO(Battle battle) {
        return BattleResponseDTO.CreateBattleResultDTO.builder()
                .userId(battle.getPost1().getUser().getId())
                .battleId(battle.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Battle toChallengeBattle (Post post, BattleRequestDTO.ChallengeBattleDTO request) { // 배틀 신청
        return Battle.builder()
                .id(post.getId())
                .build();
    }

    public static BattleResponseDTO.ChallengeBattleResultDTO challengeBattleResultDTO(Battle battle) {
        return BattleResponseDTO.ChallengeBattleResultDTO.builder()
                .firstPostId(battle.getPost1().getId())
                .secondPostId(battle.getPost2().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static BattleResponseDTO.BattlePreviewDTO battlePreviewDTO(Battle battle) { // 배틀 게시글 목록 조회
        return BattleResponseDTO.BattlePreviewDTO.builder()
                .firstUserId(battle.getPost1().getUser().getId())
                .secondUserId(battle.getPost2().getUser().getId())
                .firstPostId(battle.getPost1().getId())
                .secondPostId(battle.getPost2().getId())
                .title(battle.getTitle())
                .firstVotingCount(battle.getPost1().getVotingCount())
                .secondVotingCount(battle.getPost2().getVotingCount())
                .build();
    }

    public static BattleResponseDTO.BattlePreviewListDTO battlePreviewListDTO(Slice<Battle> battleList) {
        List<BattleResponseDTO.BattlePreviewDTO> battlePreviewDTOList = battleList.stream()
                .map(BattleConverter::battlePreviewDTO).collect(Collectors.toList());

        return BattleResponseDTO.BattlePreviewListDTO.builder()
                .battlePreviewList(battlePreviewDTOList)
                .listSize(battlePreviewDTOList.size())
                .isFirst(battleList.isFirst())
                .isLast(battleList.isLast())
                .hasNext(battleList.hasNext())
                .build();
    }
}
