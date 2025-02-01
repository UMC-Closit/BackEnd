package UMC_7th.Closit.domain.battle.converter;

import UMC_7th.Closit.domain.battle.dto.BattleDTO.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleDTO.BattleResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.Vote;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class BattleConverter {

    public static Battle toBattle (Post post, BattleRequestDTO.CreateBattleDTO request) { // 배틀 생성
        return Battle.builder()
                .post1(post)
                .title(request.getTitle())
                .build();
    }
    public static BattleResponseDTO.CreateBattleResultDTO createBattleResultDTO(Battle battle) {
        return BattleResponseDTO.CreateBattleResultDTO.builder()
                .userId(battle.getPost1().getUser().getId())
                .battleId(battle.getId())
                .deadline(battle.getDeadline())
                .createdAt(battle.getCreatedAt())
                .build();
    }

    public static BattleResponseDTO.ChallengeBattleResultDTO challengeBattleResultDTO(Battle battle) {
        return BattleResponseDTO.ChallengeBattleResultDTO.builder()
                .firstUserId(battle.getPost1().getUser().getId())
                .secondUserId(battle.getPost2().getUser().getId())
                .firstPostId(battle.getPost1().getId())
                .secondPostId(battle.getPost2().getId())
                .createdAt(battle.getCreatedAt())
                .build();
    }

    public static Vote toVote(User user, BattleRequestDTO.VoteBattleDTO request) { // 배틀 투표
        return Vote.builder()
                .user(user)
                .votedPostId(request.getPostId())
                .build();
    }

    public static BattleResponseDTO.VoteBattleResultDTO voteBattleResultDTO(Vote vote) {
        return BattleResponseDTO.VoteBattleResultDTO.builder()
                .voterId(vote.getUser().getId())
                .battleId(vote.getBattle().getId())
                .firstUserId(vote.getBattle().getPost1().getUser().getId())
                .secondUserId(vote.getBattle().getPost2().getUser().getId())
                .firstVotingCount(vote.getBattle().getFirstVotingCnt())
                .secondVotingCount(vote.getBattle().getSecondVotingCnt())
                .createdAt(vote.getBattle().getCreatedAt())
                .build();
    }

    public static BattleResponseDTO.BattlePreviewDTO battlePreviewDTO(Battle battle) { // 배틀 게시글 목록 조회
        return BattleResponseDTO.BattlePreviewDTO.builder()
                .firstUserId(battle.getPost1().getUser().getId())
                .secondUserId(battle.getPost2().getUser().getId())
                .firstPostId(battle.getPost1().getId())
                .secondPostId(battle.getPost2().getId())
                .title(battle.getTitle())
                .firstVotingCount(battle.getFirstVotingCnt())
                .secondVotingCount(battle.getSecondVotingCnt())
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

    public static BattleResponseDTO.ChallengeBattlePreviewDTO challengeBattlePreviewDTO(Battle battle) { // 배틀 챌린지 게시글 목록 조회
        return BattleResponseDTO.ChallengeBattlePreviewDTO.builder()
                .firstUserId(battle.getPost1().getUser().getId())
                .firstPostId(battle.getPost1().getId())
                .title(battle.getTitle())
                .build();
    }

    public static BattleResponseDTO.ChallengeBattlePreviewListDTO challengeBattlePreviewListDTO(Slice<Battle> challengeBattleList) {
        List<BattleResponseDTO.ChallengeBattlePreviewDTO> challengeBattlePreviewDTOList = challengeBattleList.stream()
                .map(BattleConverter::challengeBattlePreviewDTO).collect(Collectors.toList());

        return BattleResponseDTO.ChallengeBattlePreviewListDTO.builder()
                .challengeBattlePreviewList(challengeBattlePreviewDTOList)
                .listSize(challengeBattlePreviewDTOList.size())
                .isFirst(challengeBattleList.isFirst())
                .isLast(challengeBattleList.isLast())
                .hasNext(challengeBattleList.hasNext())
                .build();
    }
}