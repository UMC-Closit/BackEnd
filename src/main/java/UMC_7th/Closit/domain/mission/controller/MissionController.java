package UMC_7th.Closit.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
public class MissionController {

    @Operation(summary = "사용자의 미션 목록 조회", description = "특정 사용자의 미션 목록을 조회합니다.")
    @GetMapping("/users/{user_id}/missions")
    public ResponseEntity<List<String>> getUserMissions(@PathVariable Long user_id) {
        return ResponseEntity.ok(List.of("Mission1", "Mission2"));
    }

    @Operation(summary = "미션 생성", description = "새로운 미션을 생성합니다.")
    @PostMapping
    public ResponseEntity<String> createMission(@RequestBody String mission) {
        return ResponseEntity.ok("Created Mission: " + mission);
    }

    @Operation(summary = "미션 완료", description = "특정 미션을 완료로 표시합니다.")
    @PutMapping("/{mission_id}/complete")
    public ResponseEntity<String> completeMission(@PathVariable Long mission_id) {
        return ResponseEntity.ok("Completed Mission with ID: " + mission_id);
    }
}