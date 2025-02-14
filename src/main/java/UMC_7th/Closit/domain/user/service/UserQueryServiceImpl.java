package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.follow.repository.FollowRepository;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.highlight.repository.HighlightRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final HighlightRepository highlightRepository;
    private final FollowRepository followRepository;

    @Override
    public Slice<Highlight> getHighlightList(String clositId, Pageable pageable) {
        User user = userRepository.findByClositId(clositId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        return highlightRepository.findAllByUser(user, pageable);
    }

    @Override
    public Slice<User> getFollowerList(String clositId, Pageable pageable) {
        User user = userRepository.findByClositId(clositId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Slice<Follow> followers = followRepository.findByFollower(user, pageable);
        return followers.map(Follow::getFollowing);
    }

    @Override
    public Slice<User> getFollowingList(String clositId, Pageable pageable) {
        User user = userRepository.findByClositId(clositId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Slice<Follow> followings = followRepository.findByFollowing(user, pageable);
        return followings.map(Follow::getFollower);
    }

    @Override
    public User getUserInfo(String clositId) {
        return userRepository.findByClositId(clositId)
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));
    }

}
