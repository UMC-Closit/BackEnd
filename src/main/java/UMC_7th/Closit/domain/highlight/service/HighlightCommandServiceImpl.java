package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.converter.HighlightConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightRequestDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.repository.HighlightRepository;
import UMC_7th.Closit.domain.user.entity.Role;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import UMC_7th.Closit.global.apiPayload.exception.handler.HighlightHandler;
import UMC_7th.Closit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HighlightCommandServiceImpl implements HighlightCommandService {

    private final HighlightRepository highlightRepository;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public Highlight createHighlight(HighlightRequestDTO.CreateHighlightDTO request) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        Highlight newHighlight = HighlightConverter.toHighlight(request, user);

        return highlightRepository.save(newHighlight);
    }

    @Override
    @Transactional
    public Highlight updateHighlight(Long highlightId, HighlightRequestDTO.UpdateHighlightDTO request) {
        Highlight highlight = validateHighlightEditPermission(highlightId);
        highlight.updateHighlight(request.getTitle(), request.getThumbnail());
        return highlight;
    }

    @Override
    @Transactional
    public void deleteHighlight(Long highlightId) {
        Highlight highlight = validateHighlightEditPermission(highlightId);
        highlightRepository.delete(highlight);
    }

    // 특정 하이라이트를 수정 또는 삭제할 수 있는 권한이 있는지 확인하는 메서드
    private Highlight validateHighlightEditPermission(Long highlightId) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        Highlight highlight = highlightRepository.findById(highlightId)
                .orElseThrow(() -> new HighlightHandler(ErrorStatus.HIGHLIGHT_NOT_FOUND));

        // 자기 자신이거나 관리자 권한이 있는 경우만 허용
        if (!user.getId().equals(highlight.getUser().getId()) ||
                !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        return highlight;
    }
}
