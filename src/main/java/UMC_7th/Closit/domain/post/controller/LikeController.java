package UMC_7th.Closit.domain.post.controller;

import UMC_7th.Closit.domain.post.dto.LikeRequestDTO;
import UMC_7th.Closit.domain.post.dto.LikeResponseDTO;
import UMC_7th.Closit.domain.post.service.LikeService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{post_id}/likes")
public class LikeController {
    private final LikeService likeService;

    @Operation(summary = "게시글 좋아요 추가")
    @PostMapping
    public ApiResponse<LikeResponseDTO.LikeStatusDTO> likePost(
            @PathVariable("post_id") Long postId,
            @RequestParam("user_id") Long userId) {
        LikeRequestDTO.CreateLikeDTO request = new LikeRequestDTO.CreateLikeDTO(userId, postId);
        return ApiResponse.onSuccess(likeService.likePost(request));
    }

    @Operation(summary = "게시글 좋아요 삭제")
    @DeleteMapping("/{like_id}")
    public ApiResponse<Void> unlikePost(
            @PathVariable("post_id") Long postId,
            @PathVariable("like_id") Long likeId,
            @RequestParam("user_id") Long userId){
        LikeRequestDTO.UnlikeDTO request = new LikeRequestDTO.UnlikeDTO(userId, postId, likeId);
        likeService.unlikePost(request);
        return ApiResponse.onSuccess(null);
    }
}
