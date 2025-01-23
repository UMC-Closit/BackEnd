package UMC_7th.Closit.domain.highlight.controller;

import UMC_7th.Closit.domain.highlight.converter.HighlightPostConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightPostRequestDTO;
import UMC_7th.Closit.domain.highlight.dto.HighlightPostResponseDTO;
import UMC_7th.Closit.domain.highlight.entity.HighlightPost;
import UMC_7th.Closit.domain.highlight.service.HighlightPostCommandService;
import UMC_7th.Closit.domain.highlight.service.HighlightPostQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/highlightposts")
public class HighlightPostController {

    private final HighlightPostCommandService highlightPostCommandService;
    private final HighlightPostQueryService highlightPostQueryService;

    @Operation(summary = "하이라이트 게시글 생성", description = "하이라이트에 새로운 게시글을 생성합니다.")
    @PostMapping
    public ApiResponse<HighlightPostResponseDTO.CreateHighlightPostResultDTO> createHighlightPost(@RequestBody @Valid HighlightPostRequestDTO.CreateHighlightPostDTO request) {
        HighlightPost highlightPost = highlightPostCommandService.createHighlightPost(request);
        return ApiResponse.onSuccess(HighlightPostConverter.toCreateHighlightPostResultDTO(highlightPost));
    }

    @Operation(summary = "하이라이트 게시글 삭제", description = "특정 하이라이트에서 게시글을 삭제합니다.")
    @DeleteMapping("/{highlight_post_id}")
    public ApiResponse<String> deleteHighlightPost(@PathVariable Long highlight_post_id) {

        highlightPostCommandService.deleteHighlightPost(highlight_post_id);
        return ApiResponse.onSuccess("Deleted Highlight Post with ID: " + highlight_post_id);
    }
}
