package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.repository.HighlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HighlightQueryServiceImpl implements HighlightQueryService {

    private final HighlightRepository highlightRepository;

    @Override
    public Optional<Highlight> findHighlight(Long id) {
        return highlightRepository.findById(id);
    }
}
