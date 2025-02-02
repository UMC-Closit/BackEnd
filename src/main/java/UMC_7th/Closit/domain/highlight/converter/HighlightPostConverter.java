package UMC_7th.Closit.domain.highlight.converter;

import UMC_7th.Closit.domain.highlight.dto.HighlightPostRequestDTO;
import UMC_7th.Closit.domain.highlight.dto.HighlightPostResponseDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.entity.HighlightPost;
import UMC_7th.Closit.domain.post.entity.Post;

import java.time.LocalDateTime;

public class HighlightPostConverter {

    public static HighlightPostResponseDTO.CreateHighlightPostResultDTO toCreateHighlightPostResultDTO(HighlightPost highlightPost) {
        return HighlightPostResponseDTO.CreateHighlightPostResultDTO.builder()
                .highlightPostId(highlightPost.getId())
                .postId(highlightPost.getPost().getId())
                .highlightId(highlightPost.getHighlight().getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static HighlightPostResponseDTO.HighlightPostDTO toHighlightPostDTO(HighlightPost highlightPost) {
        return HighlightPostResponseDTO.HighlightPostDTO.builder()
                .highlightPostId(highlightPost.getId())
                .postId(highlightPost.getPost().getId())
                .build();
    }

    public static HighlightPost toHighlightPost(HighlightPostRequestDTO.CreateHighlightPostDTO request, Post post, Highlight highlight) {
        return HighlightPost.builder()
                .post(post)
                .highlight(highlight)
                .build();
    }
}
