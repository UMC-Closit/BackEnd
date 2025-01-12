package UMC_7th.Closit.domain.follow.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follows")
public class FollowController {

    @Operation(summary = "팔로우 생성", description = "새로운 팔로우를 생성합니다.")
    @PostMapping
    public ResponseEntity<String> createFollow(@RequestBody String follow) {
        return ResponseEntity.ok("Created Follow: " + follow);
    }

    @Operation(summary = "팔로우 삭제", description = "특정 팔로우를 삭제합니다.")
    @DeleteMapping("/{follow_id}")
    public ResponseEntity<String> deleteFollow(@PathVariable Long follow_id) {
        return ResponseEntity.ok("Deleted Follow with ID: " + follow_id);
    }
}
