package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.converter.HighlightConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightRequestDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.repository.HighlightRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HighlightCommandServiceImpl implements HighlightCommandService {

    private final HighlightRepository highlightRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Highlight createHighlight(HighlightRequestDTO.CreateHighlightDTO request) {
        User user = userRepository.findById(request.getUser())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Highlight newHighlight = HighlightConverter.toHighlight(request, user);

        return highlightRepository.save(newHighlight);
    }
}
