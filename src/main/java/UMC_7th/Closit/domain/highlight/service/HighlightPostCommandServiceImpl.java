package UMC_7th.Closit.domain.highlight.service;

import UMC_7th.Closit.domain.highlight.converter.HighlightPostConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightPostRequestDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.entity.HighlightPost;
import UMC_7th.Closit.domain.highlight.repository.HighlightPostRepository;
import UMC_7th.Closit.domain.highlight.repository.HighlightRepository;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.Role;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.HighlightHandler;
import UMC_7th.Closit.global.apiPayload.exception.handler.HighlightPostHandler;
import UMC_7th.Closit.global.apiPayload.exception.handler.PostHandler;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import UMC_7th.Closit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HighlightPostCommandServiceImpl implements HighlightPostCommandService {

    private final HighlightPostRepository highlightPostRepository;
    private final PostRepository postRepository;
    private final HighlightRepository highlightRepository;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public HighlightPost createHighlightPost(HighlightPostRequestDTO.CreateHighlightPostDTO request) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();

        Post post = postRepository.findById(request.getPost())
                .orElseThrow(() -> new PostHandler(ErrorStatus.POST_NOT_FOUND));
        Highlight highlight = highlightRepository.findById(request.getHighlight())
                .orElseThrow(() -> new HighlightHandler(ErrorStatus.HIGHLIGHT_NOT_FOUND));

        // 사용자가 하이라이트와 포스트에 대한 권한이 있는지 체크
        if ((!user.getId().equals(highlight.getUser().getId()) &&
                !user.getRole().equals(Role.ADMIN)) ||
                (!user.getId().equals(post.getUser().getId()) &&
                        !user.getRole().equals(Role.ADMIN))) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        HighlightPost newHighlightPost = HighlightPostConverter.toHighlightPost(request, post, highlight);

        return highlightPostRepository.save(newHighlightPost);
    }

    @Override
    @Transactional
    public void deleteHighlightPost(Long highlightPostId) {
        // 현재 로그인된 사용자 정보 가져오기
        User user = securityUtil.getCurrentUser();
        
        HighlightPost highlightPost = highlightPostRepository.findById(highlightPostId)
                .orElseThrow(() -> new HighlightPostHandler(ErrorStatus.HIGHLIGHT_POST_NOT_FOUND));

        // 사용자가 하이라이트를 소유하고 있거나, 관리자 권한이 있는지 체크
        if (!user.getId().equals(highlightPost.getHighlight().getUser().getId()) &&
                !user.getRole().equals(Role.ADMIN)) {
            throw new UserHandler(ErrorStatus.USER_NOT_AUTHORIZED);
        }

        highlightPostRepository.delete(highlightPost);
    }
}
