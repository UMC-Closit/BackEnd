package UMC_7th.Closit.domain.battle.controller;

import UMC_7th.Closit.domain.battle.converter.BattleLikeConverter;
import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeResponseDTO;
import UMC_7th.Closit.domain.battle.entity.BattleLike;
import UMC_7th.Closit.domain.battle.service.BattleLikeService.BattleLikeCommandService;
import UMC_7th.Closit.domain.battle.service.BattleLikeService.BattleLikeQueryService;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import UMC_7th.Closit.security.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth/communities/battle")
public class BattleLikeController {

    private final BattleLikeCommandService battleLikeCommandService;
    private final BattleLikeQueryService battleLikeQueryService;
    private final SecurityUtil securityUtil;

    @Operation(summary = "배틀 좋아요 생성",
            description = """
            ## 배틀 게시글 좋아요 추가
            ### PathVariable
            battle_id [배틀 ID]
            """)
    @PostMapping("/{battle_id}/likes")
    public ApiResponse<BattleLikeResponseDTO.CreateBattleLikeResultDTO> createBattleLike(@PathVariable("battle_id") Long battleId) {
        User user = securityUtil.getCurrentUser();
        Long userId = user.getId();

        BattleLike battleLike = battleLikeCommandService.createBattleLike(userId, battleId);

        return ApiResponse.onSuccess(BattleLikeConverter.createBattleLikeResultDTO(battleLike));
    }

    @Operation(summary = "배틀 좋아요 조회",
            description = """
            ## 배틀 게시글 좋아요 목록 조회
            ### PathVariable
            battle_id [배틀 ID]
            ### Parameters
            page [조회할 페이지 번호] - 0부터 시작, 10개씩 보여줌
            """)
    @GetMapping("/{battle_id}/likes")
    public ApiResponse<BattleLikeResponseDTO.BattleLikePreviewListDTO> getBattleLike(@PathVariable("battle_id") Long battleId,
                                                                                     @RequestParam(name = "page") Integer page) {

        Slice<BattleLike> battleLikeList = battleLikeQueryService.getBattleLikeList(battleId, page);

        return ApiResponse.onSuccess(BattleLikeConverter.battleLikePreviewListDTO(battleLikeList));
    }

    @Operation(summary = "배틀 좋아요 삭제",
            description = """
            ## 배틀 게시글 내 특정 좋아요 취소
            ### PathVariable
            battle_id [배틀 ID]
            """)
    @DeleteMapping("{battle_id}/likes")
    public ApiResponse<String> deleteBattleLike(@PathVariable("battle_id") Long battleId) {

        User user = securityUtil.getCurrentUser();
        Long userId = user.getId();

        battleLikeCommandService.deleteBattleLike(userId, battleId);

        return ApiResponse.onSuccess("Deletion successful");
    }
}