package UMC_7th.Closit.domain.highlight.controller;

import UMC_7th.Closit.domain.highlight.converter.HighlightConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import UMC_7th.Closit.domain.highlight.dto.HighlightRequestDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.service.HighlightCommandService;
import UMC_7th.Closit.domain.highlight.service.HighlightQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/highlights")
public class HighlightController {

    private final HighlightCommandService highlightCommandService;
    private final HighlightQueryService highlightQueryService;

    @Operation(summary = "하이라이트 상세 조회", description = "ID를 통해 특정 하이라이트의 상세 정보를 조회합니다.")
    @GetMapping("/{highlight_id}")
    public ResponseEntity<String> getHighlightById(@PathVariable Long highlight_id) {
        return ResponseEntity.ok("Highlight details with ID: " + highlight_id);
    }

    @Operation(summary = "하이라이트 생성", description = "새로운 하이라이트를 생성합니다.")
    @PostMapping
    public ApiResponse<HighlightResponseDTO.CreateHighlightResultDTO> createHighlight(@RequestBody @Valid HighlightRequestDTO.CreateHighlightDTO request) {
        Highlight highlight = highlightCommandService.createHighlight(request);
        return ApiResponse.onSuccess(HighlightConverter.toCreateHighlightResultDTO(highlight));
    }

    @Operation(summary = "하이라이트에 게시글 추가", description = "특정 하이라이트에 게시글을 추가합니다.")
    @PostMapping("/{highlights_id}")
    public ResponseEntity<String> addPhotoToHighlight(@PathVariable Long highlights_id, @RequestBody String photo) {
        return ResponseEntity.ok("Added photo to Highlight with ID: " + highlights_id);
    }

    @Operation(summary = "하이라이트 수정", description = "ID를 통해 특정 하이라이트를 수정합니다.")
    @PutMapping("/{highlight_id}")
    public ResponseEntity<String> updateHighlight(@PathVariable Long highlight_id, @RequestBody String highlight) {
        return ResponseEntity.ok("Updated Highlight with ID: " + highlight_id);
    }

    @Operation(summary = "하이라이트 삭제", description = "ID를 통해 특정 하이라이트를 삭제합니다.")
    @DeleteMapping("/{highlight_id}")
    public ResponseEntity<String> deleteHighlight(@PathVariable Long highlight_id) {
        return ResponseEntity.ok("Deleted Highlight with ID: " + highlight_id);
    }
}
