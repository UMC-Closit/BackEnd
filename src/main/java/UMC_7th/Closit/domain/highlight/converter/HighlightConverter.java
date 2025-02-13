package UMC_7th.Closit.domain.highlight.converter;

import UMC_7th.Closit.domain.highlight.dto.HighlightRequestDTO;
import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class HighlightConverter {

    public static HighlightResponseDTO.CreateHighlightResultDTO toCreateHighlightResultDTO(Highlight highlight) {
        return HighlightResponseDTO.CreateHighlightResultDTO.builder()
                .highlightId(highlight.getId())
                .userId(highlight.getUser().getId())
                .title(highlight.getTitle())
                .thumbnail(highlight.getThumbnail())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static HighlightResponseDTO.UpdateHighlightResultDTO toUpdateHighlightResultDTO(Highlight highlight) {
        return HighlightResponseDTO.UpdateHighlightResultDTO.builder()
                .highlightId(highlight.getId())
                .userId(highlight.getUser().getId())
                .title(highlight.getTitle())
                .thumbnail(highlight.getThumbnail())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static HighlightResponseDTO.HighlightDTO toHighlightDTO(Highlight highlight) {
        return HighlightResponseDTO.HighlightDTO.builder()
                .highlightId(highlight.getId())
                .clositId(highlight.getUser().getClositId())
                .title(highlight.getTitle())
                .thumbnail(highlight.getThumbnail())
                .createdAt(highlight.getCreatedAt())
                .updatedAt(highlight.getUpdatedAt())
                .build();
    }

    public static HighlightResponseDTO.HighlightDetailDTO toHighlightDetailDTO(Highlight highlight) {
        return HighlightResponseDTO.HighlightDetailDTO.builder()
                .highlightId(highlight.getId())
                .clositId(highlight.getUser().getClositId())
                .title(highlight.getTitle())
                .thumbnail(highlight.getThumbnail())
                .createdAt(highlight.getCreatedAt())
                .updatedAt(highlight.getUpdatedAt())
                .highlightPosts(
                        highlight.getHighlightPosts().stream()
                                .map(HighlightPostConverter::toHighlightPostDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public static Highlight toHighlight(HighlightRequestDTO.CreateHighlightDTO request, User user) {
        return Highlight.builder()
                .user(user)
                .title(request.getTitle())
                .thumbnail(request.getThumbnail())
                .build();
    }
}
