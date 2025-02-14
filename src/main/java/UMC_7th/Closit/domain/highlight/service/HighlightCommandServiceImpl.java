package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.converter.HighlightConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightRequestDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.repository.HighlightRepository;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import UMC_7th.Closit.global.apiPayload.exception.handler.PostHandler;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import UMC_7th.Closit.global.apiPayload.exception.handler.HighlightHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HighlightCommandServiceImpl implements HighlightCommandService {

    private final HighlightRepository highlightRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Highlight createHighlight(HighlightRequestDTO.CreateHighlightDTO request) {
        User user = userRepository.findById(request.getUser())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Post post = postRepository.findById(request.getPost())
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));

        Highlight newHighlight = HighlightConverter.toHighlight(request, user, post);

        return highlightRepository.save(newHighlight);
    }

    @Override
    @Transactional
    public void deleteHighlight(Long highlightId) {
        Highlight highlight = highlightRepository.findById(highlightId)
                .orElseThrow(() -> new HighlightHandler(ErrorStatus.HIGHLIGHT_NOT_FOUND));

        highlightRepository.delete(highlight);
    }
}
