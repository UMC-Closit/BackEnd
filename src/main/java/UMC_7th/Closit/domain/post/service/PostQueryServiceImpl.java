package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.follow.repository.FollowRepository;
import UMC_7th.Closit.domain.post.entity.Hashtag;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.HashTagRepository;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
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
    private final UserRepository userRepository;

    public Slice<Post> getPostListByFollowerAndHashtag(Long userId, boolean follower, String hashtag, Pageable pageable) {
        User user = userRepository.findById(userId).orElse(null);

        // 팔로워가 `true`인 경우
        if (follower) {
            // 팔로워 기반 조회
            List<User> followingUsers = followRepository.findByFollowing(user).stream()
                    .map(Follow::getFollower)
                    .collect(Collectors.toList());

            // 해시태그가 있을 경우 → 팔로우한 유저의 글 중 해당 해시태그 포함한 글 검색
            if (hashtag != null && !hashtag.isBlank()) {
                Hashtag foundHashtag = hashtagRepository.findByContent(hashtag)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.HASHTAG_NOT_FOUND));
                return postRepository.findByUsersAndHashtagId(followingUsers, foundHashtag.getId(), pageable);
            }

            // 해시태그가 없을 경우 → 그냥 팔로우한 유저들의 전체 게시글 반환
            return postRepository.findByUsers(followingUsers, pageable);
        }

        // 팔로워가 `false`인 경우 → 전체 게시글 중 해시태그 여부에 따라 검색
        if (hashtag != null && !hashtag.isBlank()) {
            Hashtag foundHashtag = hashtagRepository.findByContent(hashtag)
                    .orElseThrow(() -> new GeneralException(ErrorStatus.HASHTAG_NOT_FOUND));
            return postRepository.findByHashtagId(foundHashtag.getId(), pageable);
        }

        // 팔로워가 `false`이고 해시태그도 없으면 전체 게시글 최신순 반환
        return postRepository.findAllByOrderByCreatedAtDesc(pageable);
    }
}

