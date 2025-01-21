package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.dto.HighlightPostRequestDTO;
import UMC_7th.Closit.domain.highlight.entity.HighlightPost;

public interface HighlightPostCommandService {
    
    HighlightPost createHighlightPost(HighlightPostRequestDTO.CreateHighlightPostDTO request);

    void deleteHighlightPost(Long highlightPostId);
}
