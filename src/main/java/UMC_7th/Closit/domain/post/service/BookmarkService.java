package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.dto.BookmarkRequestDTO;
import UMC_7th.Closit.domain.post.dto.BookmarkResponseDTO;

import java.util.List;

public interface BookmarkService {
    BookmarkResponseDTO.BookmarkStatusDTO addBookmark(BookmarkRequestDTO.BookmarkDTO request);
    List<BookmarkResponseDTO.BookmarkStatusDTO> getUserBookmarks(String clositId);
    void removeBookmark(Long bookmarkId);
}
