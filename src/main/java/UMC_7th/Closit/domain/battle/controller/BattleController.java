package UMC_7th.Closit.domain.battle.controller;

import UMC_7th.Closit.domain.battle.converter.BattleConverter;
import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.Vote;
import UMC_7th.Closit.domain.battle.service.BattleCommandService;
import UMC_7th.Closit.domain.battle.service.BattleQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class BattleController {

    private final BattleCommandService battleCommandService;
    private final BattleQueryService battleQueryService;

    @Operation(summary = "새로운 배틀 생성")
    @PostMapping("/communities/battle/upload")
    public ApiResponse<BattleResponseDTO.CreateBattleResultDTO> createBattle(@RequestBody @Valid BattleRequestDTO.CreateBattleDTO request) {

        Battle battle = battleCommandService.createBattle(request);

        return ApiResponse.onSuccess(BattleConverter.createBattleResultDTO(battle));
    }

    @Operation(summary = "배틀 신청")
    @PostMapping("/communities/battle/challenge/upload/{battle_id}")
    public ApiResponse<BattleResponseDTO.ChallengeBattleResultDTO> challengeBattle(@RequestBody @Valid BattleRequestDTO.ChallengeBattleDTO request,
                                                                                   @PathVariable("battle_id") Long battleId) {

        Battle challengeBattle = battleCommandService.challengeBattle(battleId, request);

        return ApiResponse.onSuccess(BattleConverter.challengeBattleResultDTO(challengeBattle));
    }

    @Operation(summary = "배틀 투표")
    @PostMapping("/communities/battle/{battle_id}/voting")
    public ApiResponse<BattleResponseDTO.VoteBattleResultDTO> voteBattle(@RequestBody @Valid BattleRequestDTO.VoteBattleDTO request,
                                                                         @PathVariable("battle_id") Long battleId) {

        Vote voteBattle = battleCommandService.voteBattle(battleId, request);

        return ApiResponse.onSuccess(BattleConverter.voteBattleResultDTO(voteBattle));
    }

    @Operation(summary = "배틀 게시글 목록 조회")
    @GetMapping("/communities/battle")
    public ApiResponse<BattleResponseDTO.BattlePreviewListDTO> getBattleList(@RequestParam(name = "page") Integer page) {

        Slice<Battle> battleList = battleQueryService.getBattleList(page);

        return ApiResponse.onSuccess(BattleConverter.battlePreviewListDTO(battleList));
    }

    @Operation(summary = "배틀 챌린지 게시글 목록 조회")
    @GetMapping("/communities/battle/challenge")
    public ApiResponse<BattleResponseDTO.ChallengeBattlePreviewListDTO> getChallengeBattleList(@RequestParam(name = "page") Integer page) {

        Slice<Battle> challengeBattleList = battleQueryService.getChallengeBattleList(page);

        return ApiResponse.onSuccess(BattleConverter.challengeBattlePreviewListDTO(challengeBattleList));
    }

    @Operation(summary = "배틀 삭제")
    @DeleteMapping("/communities/battle/{battle_id}")
    public ApiResponse<Void> deleteBattle(@PathVariable("battle_id") Long battleId) {

        battleCommandService.deleteBattle(battleId);

        return ApiResponse.onSuccess(null);
    }
}
