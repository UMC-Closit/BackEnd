package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.follow.repository.FollowRepository;
import UMC_7th.Closit.domain.post.entity.Hashtag;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.HashTagRepository;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import UMC_7th.Closit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostQueryServiceImpl implements PostQueryService {

    private final PostRepository postRepository;
    private final FollowRepository followRepository;
    private final HashTagRepository hashtagRepository;
    private final SecurityUtil securityUtil;

    public Slice<Post> getPostListByFollowing (boolean follower, Pageable pageable) {
        User currentUser = securityUtil.getCurrentUser();

        // `follower`가 `true`인 경우, 팔로우한 유저들의 게시글만 조회
        if (follower) {
            // 팔로워 기반 조회
            List<User> followingUsers = followRepository.findBySender(currentUser).stream()
                    .map(Follow::getReceiver)
                    .collect(Collectors.toList());

            return postRepository.findByUsers(followingUsers, pageable);
        }

        // `follower`가 `false`인 경우, 모든 사용자의 최신 게시글 조회
        return postRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Slice<Post> getPostListByHashtag(String hashtag, Pageable pageable) {
        // 해시태그 값이 없으면 예외 처리
        if (hashtag == null || hashtag.isBlank()) {
            throw new GeneralException(ErrorStatus.HASHTAG_NOT_FOUND);
        }

        // 해당 해시태그가 존재하는지 확인
        Hashtag foundHashtag = hashtagRepository.findByContent(hashtag)
                .orElseThrow(() -> new GeneralException(ErrorStatus.HASHTAG_NOT_FOUND));

        // 해당 해시태그가 포함된 게시글 조회
        return postRepository.findByHashtagId(foundHashtag.getId(), pageable);
    }
}

