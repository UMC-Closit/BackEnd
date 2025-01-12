package UMC_7th.Closit.domain.battle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "battle", description = "battle-API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/communities/battle")
public class BattleController {

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
    @Operation(summary = "새로운 배틀 생성")
    @PostMapping("/")
    public ResponseEntity<String> postBattle() {
        return ResponseEntity.ok("create battle");
    }

    @Operation(summary = "배틀 삭제")
    @DeleteMapping("/{battle_id}")
    public ResponseEntity<String> deleteBattle(@PathVariable("battle_id") String battleId) {
        return ResponseEntity.ok("delete battle");
    }

}
