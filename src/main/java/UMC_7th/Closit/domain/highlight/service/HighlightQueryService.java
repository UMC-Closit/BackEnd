package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;

import java.util.Optional;

public interface HighlightQueryService {

    Highlight findHighlight(Long id);
}
