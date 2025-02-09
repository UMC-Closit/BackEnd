package UMC_7th.Closit.domain.follow.service;

import UMC_7th.Closit.domain.follow.repository.FollowRepository;
import UMC_7th.Closit.domain.follow.converter.FollowConverter;
import UMC_7th.Closit.domain.follow.dto.FollowRequestDTO;
import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.notification.service.NotiCommandService;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.handler.FollowHandler;
import UMC_7th.Closit.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowCommandServiceImpl implements FollowCommandService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final NotiCommandService notiCommandService;

    @Override
    @Transactional
    public Follow createFollow(FollowRequestDTO.CreateFollowDTO request) {
        User follower = userRepository.findById(request.getFollower())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        User following = userRepository.findById(request.getFollowing())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        Follow newFollow = FollowConverter.toFollow(request, follower, following);

        // 팔로우 알림
        notiCommandService.followNotification(newFollow);

        return followRepository.save(newFollow);
    }

    @Override
    @Transactional
    public void deleteFollow(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new FollowHandler(ErrorStatus.FOLLOW_NOT_FOUND));

        followRepository.delete(follow);
    }
}
