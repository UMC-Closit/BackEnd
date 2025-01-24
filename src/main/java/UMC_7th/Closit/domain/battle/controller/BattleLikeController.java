package UMC_7th.Closit.domain.battle.controller;

import UMC_7th.Closit.domain.battle.converter.BattleLikeConverter;
import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleLikeDTO.BattleLikeResponseDTO;
import UMC_7th.Closit.domain.battle.entity.BattleLike;
import UMC_7th.Closit.domain.battle.service.BattleLikeService.BattleLikeService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class BattleLikeController {

    private final BattleLikeService battleLikeService;

    @Operation(summary = "배틀 좋아요 생성")
    @PostMapping("/communities/battle/{battle_id}/likes")
    public ApiResponse<BattleLikeResponseDTO.CreateBattleLikeResultDTO> createBattleLike(@RequestBody @Valid BattleLikeRequestDTO.CreateBattleLikeDTO request,
                                                                                         @PathVariable("battle_id") Long battleId) {

        BattleLike battleLike = battleLikeService.createBattleLike(battleId, request);

        return ApiResponse.onSuccess(BattleLikeConverter.createBattleLikeResultDTO(battleLike));
    }

    @Operation(summary = "배틀 좋아요 삭제")
    @DeleteMapping("communities/battle/{battle_id}/likes/{battle_like_id}")
    public ApiResponse<Void> deleteBattleLike(@PathVariable("battle_id") Long battleId,
                                              @PathVariable("battle_like_id") Long battleLikeId) {

        battleLikeService.deleteBattleLike(battleId, battleLikeId);

        return ApiResponse.onSuccess(null);
    }
}