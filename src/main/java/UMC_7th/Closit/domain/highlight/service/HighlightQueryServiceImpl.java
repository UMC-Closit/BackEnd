package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.repository.HighlightRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.HighlightHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HighlightQueryServiceImpl implements HighlightQueryService {

    private final HighlightRepository highlightRepository;

    @Override
    public Highlight findHighlight(Long id) {

        return highlightRepository.findByIdWithPosts(id)
                .orElseThrow(() -> new HighlightHandler(ErrorStatus.HIGHLIGHT_NOT_FOUND));
    }
}
