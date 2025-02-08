package UMC_7th.Closit.domain.post.controller;

import UMC_7th.Closit.domain.post.converter.CommentConverter;
import UMC_7th.Closit.domain.post.dto.CommentRequestDTO;
import UMC_7th.Closit.domain.post.dto.CommentResponseDTO;
import UMC_7th.Closit.domain.post.entity.Comment;
import UMC_7th.Closit.domain.post.service.CommentCommandService;
import UMC_7th.Closit.domain.post.service.CommentQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/posts/{post_id}/comments")
public class CommentController {
    private final CommentCommandService commentCommandService;
    private final CommentQueryService commentQueryService;

    @Operation(summary = "새로운 댓글 생성")
    @PostMapping
    public ApiResponse<CommentResponseDTO.CreateCommentResultDTO> createComment(
            @RequestBody @Valid CommentRequestDTO.CreateCommentRequestDTO request,
            @PathVariable("post_id") Long postId) {

        Comment comment = commentCommandService.createComment(postId, request);

        return ApiResponse.onSuccess(CommentConverter.createCommentResponseDTO(comment));
    }

    @Operation(summary = "댓글 조회")
    @GetMapping
    public ApiResponse<CommentResponseDTO.CommentPreviewListDTO> getComments(
            @PathVariable("post_id") Long postId,
            @RequestParam(name = "page") Integer page) {

        Slice<Comment> commentList = commentQueryService.getCommentList(postId, page);

        return ApiResponse.onSuccess(CommentConverter.commentPreviewListDTO(commentList));
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{comment_id}")
    public ApiResponse<String> deleteComment(
            @PathVariable("post_id") Long postId,
            @PathVariable("comment_id") Long commentId) {

        commentCommandService.deleteComment(postId, commentId);

        return ApiResponse.onSuccess("Deletion successful");
    }
}
