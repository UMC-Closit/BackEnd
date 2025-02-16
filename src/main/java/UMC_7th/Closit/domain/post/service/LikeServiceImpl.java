package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.notification.service.NotiCommandService;
import UMC_7th.Closit.domain.post.converter.LikeConverter;
import UMC_7th.Closit.domain.post.dto.LikeRequestDTO;
import UMC_7th.Closit.domain.post.dto.LikeResponseDTO;
import UMC_7th.Closit.domain.post.dto.PostResponseDTO;
import UMC_7th.Closit.domain.post.entity.Likes;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.LikeRepository;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private final NotiCommandService notiCommandService;

    @Override
    public LikeResponseDTO.LikeStatusDTO likePost(LikeRequestDTO.CreateLikeDTO request) {
        User user = userRepository.findByClositId(request.getClositId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        if (likeRepository.existsByUserAndPost(user, post)) {
            throw new GeneralException(ErrorStatus.LIKES_ALREADY_EXIST);
        }

        Likes likes = Likes.createLikes(user, post);
        likeRepository.save(likes);

        // 좋아요 알림
        notiCommandService.likeNotification(likes);

        return LikeConverter.toLikeStatusDTO(post, user, true);
    }

    @Override
    public LikeResponseDTO.LikeStatusDTO unlikePost(LikeRequestDTO.UnlikeDTO request) {
        User user = userRepository.findByClositId(request.getClositId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));
        Likes like = likeRepository.findById(request.getLikeId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.LIKES_NOT_FOUND));
        if (!like.getPost().getId().equals(post.getId())){
            throw new GeneralException(ErrorStatus._BAD_REQUEST);
        }

        likeRepository.delete(like);

        return LikeConverter.toLikeStatusDTO(post, user, false);
    }

}
