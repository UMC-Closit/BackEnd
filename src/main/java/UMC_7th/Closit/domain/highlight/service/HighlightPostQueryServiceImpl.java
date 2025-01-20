package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.entity.HighlightPost;
import UMC_7th.Closit.domain.highlight.repository.HighlightPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HighlightPostQueryServiceImpl implements HighlightPostQueryService {

    private final HighlightPostRepository highlightPostRepository;

    @Override
    public Optional<HighlightPost> findHighlightPost(Long id) {
        return Optional.empty();
    }
}
