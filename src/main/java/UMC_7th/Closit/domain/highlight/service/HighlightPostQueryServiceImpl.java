package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.entity.HighlightPost;
import UMC_7th.Closit.domain.highlight.repository.HighlightPostRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.HighlightPostHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HighlightPostQueryServiceImpl implements HighlightPostQueryService {

    private final HighlightPostRepository highlightPostRepository;

    @Override
    public HighlightPost findHighlightPost(Long id) {

        return highlightPostRepository.findById(id)
                .orElseThrow(() -> new HighlightPostHandler(ErrorStatus.HIGHLIGHT_POST_NOT_FOUND));
    }
}
