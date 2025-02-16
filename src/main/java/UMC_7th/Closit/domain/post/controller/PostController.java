package UMC_7th.Closit.domain.post.controller;

import UMC_7th.Closit.domain.post.converter.PostConverter;
import UMC_7th.Closit.domain.post.dto.PostRequestDTO;
import UMC_7th.Closit.domain.post.dto.PostResponseDTO;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.service.PostCommandService;
import UMC_7th.Closit.domain.post.service.PostQueryService;
import UMC_7th.Closit.domain.post.service.PostService;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/posts")
public class PostController {

    private final PostService postService;
    private final PostQueryService postQueryService;
    private final PostCommandService postCommandService;

    @Operation(summary = "게시글 업로드")
    @PostMapping
    public ApiResponse<PostResponseDTO.CreatePostResultDTO> createPost (@RequestBody @Valid PostRequestDTO.CreatePostDTO request) {

        Post post = postCommandService.createPost(request);

        return ApiResponse.onSuccess(PostConverter.toCreatePostResultDTO(post));
    }

    @Operation(summary = "특정 게시글 조회")
    @GetMapping("/{post_id}")
    public ApiResponse<PostResponseDTO.PostPreviewDTO> getPostById(@PathVariable("post_id") Long postId,
                                                                   @AuthenticationPrincipal User currentUser) {

        PostResponseDTO.PostPreviewDTO postPreviewDTO = postService.getPostById(postId, currentUser);

        return ApiResponse.onSuccess(postPreviewDTO);
    }

    @Operation(summary = "게시글 목록 조회")
    @GetMapping
    public ApiResponse<PostResponseDTO.PostPreviewListDTO> getPostList(
            PostRequestDTO.GetPostDTO request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Slice<Post> posts = postQueryService.getPostListByFollowerAndHashtag(request, pageable);
        PostResponseDTO.PostPreviewListDTO response = PostConverter.toPostPreviewListDTO(posts);

        return ApiResponse.onSuccess(response);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{post_id}")
    public ApiResponse<PostResponseDTO.UpdatePostResultDTO> updatePost(@PathVariable("post_id") Long postId,
                                        @RequestBody @Valid PostRequestDTO.UpdatePostDTO request) {
        Post post = postCommandService.updatePost(postId, request);
        return ApiResponse.onSuccess(PostConverter.toUpdatePostResultDTO(post));
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{post_id}")
    public ApiResponse<Void> deletePost(@PathVariable("post_id") Long postId) {
        postCommandService.deletePost(postId);
        return ApiResponse.onSuccess(null);
    }
}

