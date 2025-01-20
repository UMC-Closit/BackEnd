package UMC_7th.Closit.domain.highlight.converter;

import UMC_7th.Closit.domain.highlight.dto.HighlightRequestDTO;
import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.user.entity.User;

import java.time.LocalDateTime;

public class HighlightConverter {

    public static HighlightResponseDTO.CreateHighlightResultDTO toCreateHighlightResultDTO(Highlight highlight) {
        return HighlightResponseDTO.CreateHighlightResultDTO.builder()
                .highlightId(highlight.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static HighlightResponseDTO.HighlightDetailDTO toHighlightDetailDTO(Highlight highlight) {
        return HighlightResponseDTO.HighlightDetailDTO.builder()
                .highlightId(highlight.getId())
                .userId(highlight.getUser().getId())
                .title(highlight.getTitle())
                .thumbnail(highlight.getThumbnail())
                .createdAt(highlight.getCreatedAt())
                .build();
    }

    public static Highlight toHighlight(HighlightRequestDTO.CreateHighlightDTO request, User user) {
        return Highlight.builder()
                .title(request.getTitle())
                .thumbnail(request.getThumbnail())
                .user(user)
                .build();
    }
}
