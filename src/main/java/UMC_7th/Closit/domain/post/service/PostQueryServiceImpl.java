package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.follow.repository.FollowRepository;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.PostHashTagRepository;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
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
    private final PostHashTagRepository postHashTagRepository;

    public Slice<Post> getPostListByFollowerAndHashtag(User currentUser, boolean follower, Long hashtagId, Pageable pageable) {
        if (follower) {
            // 팔로워 기반 조회
            List<User> followingUsers = followRepository.findByFollower(currentUser).stream()
                    .map(Follow::getFollowing)
                    .collect(Collectors.toList());
            return postRepository.findByUserInAndHashtagId(followingUsers, hashtagId, pageable);
        } else {
            // 해시태그 기반 조회
            return postRepository.findByHashtagId(hashtagId, pageable);
        }
    }
}

