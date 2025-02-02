package UMC_7th.Closit.domain.battle.controller;

import UMC_7th.Closit.domain.battle.converter.BattleLikeConverter;
import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeResponseDTO;
import UMC_7th.Closit.domain.battle.entity.BattleLike;
import UMC_7th.Closit.domain.battle.service.BattleLikeService.BattleLikeCommandService;
import UMC_7th.Closit.domain.battle.service.BattleLikeService.BattleLikeQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth/communities/battle")
public class BattleLikeController {

    private final BattleLikeCommandService battleLikeCommandService;
    private final BattleLikeQueryService battleLikeQueryService;

    @Operation(summary = "배틀 좋아요 생성")
    @PostMapping("/{battle_id}/likes")
    public ApiResponse<BattleLikeResponseDTO.CreateBattleLikeResultDTO> createBattleLike(@RequestBody @Valid BattleLikeRequestDTO.CreateBattleLikeDTO request,
                                                                                         @PathVariable("battle_id") Long battleId) {

        BattleLike battleLike = battleLikeCommandService.createBattleLike(battleId, request);

        return ApiResponse.onSuccess(BattleLikeConverter.createBattleLikeResultDTO(battleLike));
    }

    @Operation(summary = "배틀 좋아요 조회")
    @GetMapping("/{battle_id}/likes")
    public ApiResponse<BattleLikeResponseDTO.BattleLikePreviewListDTO> getBattleLike(@PathVariable("battle_id") Long battleId,
                                                                                     @RequestParam(name = "page") Integer page) {

        Slice<BattleLike> battleLikeList = battleLikeQueryService.getBattleLikeList(battleId, page);

        return ApiResponse.onSuccess(BattleLikeConverter.battleLikePreviewListDTO(battleLikeList));
    }

    @Operation(summary = "배틀 좋아요 삭제")
    @DeleteMapping("{battle_id}/likes/{battle_like_id}")
    public ApiResponse<String> deleteBattleLike(@PathVariable("battle_id") Long battleId,
                                              @PathVariable("battle_like_id") Long battleLikeId) {

        battleLikeCommandService.deleteBattleLike(battleId, battleLikeId);

        return ApiResponse.onSuccess("Deletion Success battleLike");
    }
}