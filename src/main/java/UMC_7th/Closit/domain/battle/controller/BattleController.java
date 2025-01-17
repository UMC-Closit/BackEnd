package UMC_7th.Closit.domain.battle.controller;

import UMC_7th.Closit.domain.battle.converter.BattleConverter;
import UMC_7th.Closit.domain.battle.dto.BattleRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleResponseDTO;
import UMC_7th.Closit.domain.battle.entity.Battle;
import UMC_7th.Closit.domain.battle.repository.BattleRepository;
import UMC_7th.Closit.domain.battle.service.BattleCommandService;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/communities/battle")
public class BattleController {

    private final BattleCommandService battleCommandService;

    @Operation(summary = "새로운 배틀 생성")
    @PostMapping("/upload/{post_id}")
    public ApiResponse<BattleResponseDTO.CreateBattleResultDTO> createBattle(@RequestBody @Valid BattleRequestDTO.CreateBattleDTO request,
                                                                             @PathVariable(name = "postId") Long postId) {

        Battle battle = battleCommandService.createBattle(postId, request);

        return ApiResponse.onSuccess(BattleConverter.createBattleResultDTO(battle));
    }

    @Operation(summary = "배틀 게시판 목록 조회")
    @GetMapping("/")
    public ResponseEntity<String> getBattle() {
        return ResponseEntity.ok("communities battle");
    }

    @Operation(summary = "배틀 챌린지 게시판 목록 조회")
    @GetMapping("/challenge")
    public ResponseEntity<String> getChallenge() {
        return ResponseEntity.ok("challenge");
    }

    @Operation(summary = "배틀 신청")
    @PostMapping("/challenge/{battle_id}")
    public ResponseEntity<String> postChallenge(@PathVariable("battle_id") String battleId) {
        return ResponseEntity.ok("battle challenge");
    }

    @Operation(summary = "배틀 투표")
    @PostMapping("/challenge/{battle_id}/votes")
    public ResponseEntity<String> postVotes(@PathVariable("battle_id") String battleId) {
        return ResponseEntity.ok("battle votes");
    }

    @Operation(summary = "배틀 삭제")
    @DeleteMapping("/{battle_id}")
    public ResponseEntity<String> deleteBattle(@PathVariable("battle_id") String battleId) {
        return ResponseEntity.ok("delete battle");
    }

}
