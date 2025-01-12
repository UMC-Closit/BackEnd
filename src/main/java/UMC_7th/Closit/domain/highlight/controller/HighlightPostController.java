package UMC_7th.Closit.domain.highlight.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/highlightposts")
public class HighlightPostController {

    @Operation(summary = "하이라이트 게시글 삭제", description = "특정 하이라이트 게시글을 삭제합니다.")
    @DeleteMapping("/{highlight_post_id}")
    public ResponseEntity<String> deleteHighlightPost(@PathVariable Long highlight_post_id) {
        return ResponseEntity.ok("Deleted Highlight Post with ID: " + highlight_post_id);
    }
}
