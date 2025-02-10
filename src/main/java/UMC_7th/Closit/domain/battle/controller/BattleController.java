package UMC_7th.Closit.domain.battle.controller;

import UMC_7th.Closit.domain.battle.converter.BattleConverter;
import UMC_7th.Closit.domain.battle.dto.BattleDTO.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleDTO.BattleResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.entity.Vote;
import UMC_7th.Closit.domain.battle.service.BattleService.BattleCommandService;
import UMC_7th.Closit.domain.battle.service.BattleService.BattleQueryService;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import UMC_7th.Closit.security.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/communities/battle")
public class BattleController {

    private final BattleCommandService battleCommandService;
    private final BattleQueryService battleQueryService;
    private final SecurityUtil securityUtil;

    @Operation(summary = "새로운 배틀 생성")
    @PostMapping("/upload")
    public ApiResponse<BattleResponseDTO.CreateBattleResultDTO> createBattle(@RequestBody @Valid BattleRequestDTO.CreateBattleDTO request) {

        User user = securityUtil.getCurrentUser();
        Long userId = user.getId();

        Battle battle = battleCommandService.createBattle(userId, request);

        return ApiResponse.onSuccess(BattleConverter.createBattleResultDTO(battle));
    }

    @Operation(summary = "배틀 신청")
    @PostMapping("/challenge/upload/{battle_id}")
    public ApiResponse<BattleResponseDTO.ChallengeBattleResultDTO> challengeBattle(@RequestBody @Valid BattleRequestDTO.ChallengeBattleDTO request,
                                                                                   @PathVariable("battle_id") Long battleId) {

        User user = securityUtil.getCurrentUser();
        Long userId = user.getId();

        Battle challengeBattle = battleCommandService.challengeBattle(userId, battleId, request);

        return ApiResponse.onSuccess(BattleConverter.challengeBattleResultDTO(challengeBattle));
    }

    @Operation(summary = "배틀 투표")
    @PostMapping("/{battle_id}/voting")
    public ApiResponse<BattleResponseDTO.VoteBattleResultDTO> voteBattle(@RequestBody @Valid BattleRequestDTO.VoteBattleDTO request,
                                                                         @PathVariable("battle_id") Long battleId) {

        User user = securityUtil.getCurrentUser();
        Long userId = user.getId();

        Vote voteBattle = battleCommandService.voteBattle(userId, battleId, request);

        return ApiResponse.onSuccess(BattleConverter.voteBattleResultDTO(voteBattle));
    }

    @Operation(summary = "배틀 게시글 목록 조회")
    @GetMapping()
    public ApiResponse<BattleResponseDTO.BattlePreviewListDTO> getBattleList(@RequestParam(name = "page") Integer page) {

        Slice<Battle> battleList = battleQueryService.getBattleList(page);

        return ApiResponse.onSuccess(BattleConverter.battlePreviewListDTO(battleList));
    }

    @Operation(summary = "배틀 챌린지 게시글 목록 조회")
    @GetMapping("/challenge")
    public ApiResponse<BattleResponseDTO.ChallengeBattlePreviewListDTO> getChallengeBattleList(@RequestParam(name = "page") Integer page) {

        Slice<Battle> challengeBattleList = battleQueryService.getChallengeBattleList(page);

        return ApiResponse.onSuccess(BattleConverter.challengeBattlePreviewListDTO(challengeBattleList));
    }

    @Operation(summary = "배틀 삭제")
    @DeleteMapping("/{battle_id}")
    public ApiResponse<String> deleteBattle(@PathVariable("battle_id") Long battleId) {

        User user = securityUtil.getCurrentUser();
        Long userId = user.getId();

        battleCommandService.deleteBattle(userId, battleId);

        return ApiResponse.onSuccess("Deletion successful");
    }
}
