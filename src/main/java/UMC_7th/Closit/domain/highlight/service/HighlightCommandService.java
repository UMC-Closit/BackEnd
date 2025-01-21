package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.dto.HighlightRequestDTO;

public interface HighlightCommandService {

    Highlight createHighlight(HighlightRequestDTO.CreateHighlightDTO request);

    Highlight updateHighlight(Long highlightId, HighlightRequestDTO.UpdateHighlightDTO request);

    void deleteHighlight(Long highlightId);
}
