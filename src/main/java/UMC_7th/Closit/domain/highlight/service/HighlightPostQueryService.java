package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.entity.HighlightPost;

import java.util.Optional;

public interface HighlightPostQueryService {

    Optional<HighlightPost> findHighlightPost(Long id);
}
