package UMC_7th.Closit.domain.post.converter;

import UMC_7th.Closit.domain.post.dto.BookmarkResponseDTO;
import UMC_7th.Closit.domain.post.entity.Bookmark;

public class BookmarkConverter {
    public static BookmarkResponseDTO.CreateBookmarkResultDTO toBookmarkStatusDTO(Bookmark bookmark) { // 북마크 생성
        return BookmarkResponseDTO.CreateBookmarkResultDTO.builder()
                .clositId(bookmark.getUser().getClositId())
                .userName(bookmark.getUser().getName())
                .bookmarkId(bookmark.getId())
                .postId(bookmark.getPost().getId())
                .createdAt(bookmark.getCreatedAt())
                .build();
    }
}
