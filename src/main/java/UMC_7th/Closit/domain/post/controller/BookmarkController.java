package UMC_7th.Closit.domain.post.controller;

import UMC_7th.Closit.domain.post.dto.BookmarkRequestDTO;
import UMC_7th.Closit.domain.post.dto.BookmarkResponseDTO;
import UMC_7th.Closit.domain.post.service.BookmarkService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;
    @Operation(summary = "게시글 북마크 추가")
    @PostMapping
    public ApiResponse<BookmarkResponseDTO.CreateBookmarkResultDTO> addBookmark(
            @RequestBody @Valid BookmarkRequestDTO.CreateBookmarkDTO request){
        return ApiResponse.onSuccess(bookmarkService.addBookmark(request));
    }

    @Operation(summary = "사용자의 북마크 목록 조회")
    @GetMapping("/{closit_id}")
    public ApiResponse<List<BookmarkResponseDTO.CreateBookmarkResultDTO>> getUserBookmarks(
            @PathVariable("closit_id") String clositId) {
        return ApiResponse.onSuccess(bookmarkService.getUserBookmarks(clositId));
    }

    @Operation(summary = "게시글 북마크 삭제")
    @DeleteMapping("/{bookmark_id}")
    public ApiResponse<String> deleteBookmark(@PathVariable("bookmark_id") Long bookmarkId){
        bookmarkService.removeBookmark(bookmarkId);
        return ApiResponse.onSuccess("Deletion successful");
    }
}
