package UMC_7th.Closit.domain.post.converter;

import UMC_7th.Closit.domain.post.dto.BookmarkResponseDTO;
import UMC_7th.Closit.domain.post.entity.Bookmark;
import UMC_7th.Closit.domain.post.repository.BookmarkRepository;

public class BookmarkConverter {
    public static BookmarkResponseDTO.BookmarkStatusDTO toBookmarkStatusDTO(Bookmark bookmark) {
        return BookmarkResponseDTO.BookmarkStatusDTO.builder()
                .bookmarkId(bookmark.getId())
                .postId(bookmark.getPost().getId())
                .userId(bookmark.getUser().getId())
                .build();
    }
}
