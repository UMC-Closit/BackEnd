package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.notification.service.NotiCommandService;
import UMC_7th.Closit.domain.post.converter.CommentConverter;
import UMC_7th.Closit.domain.post.dto.CommentRequestDTO;
import UMC_7th.Closit.domain.post.entity.Comment;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.CommentRepository;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import UMC_7th.Closit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentCommandServiceImpl implements CommentCommandService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final NotiCommandService notiCommandService;
    private final SecurityUtil securityUtil;

    @Override
    @Transactional
    public Comment createComment(Long postId, CommentRequestDTO.CreateCommentRequestDTO request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        User user = securityUtil.getCurrentUser();

        Comment comment = CommentConverter.toComment(user, post, request);

        // 댓글 알림
        notiCommandService.commentNotification(comment);

        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.COMMENT_NOT_FOUND));

        commentRepository.delete(comment);
    }
}

