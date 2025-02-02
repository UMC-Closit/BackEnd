package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.entity.HighlightPost;

import java.util.Optional;

public interface HighlightPostQueryService {

    HighlightPost findHighlightPost(Long id);
}
