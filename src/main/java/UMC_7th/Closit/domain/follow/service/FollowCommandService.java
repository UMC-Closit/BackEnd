package UMC_7th.Closit.domain.follow.service;

import UMC_7th.Closit.domain.follow.entity.Follow;
import UMC_7th.Closit.domain.follow.dto.FollowRequestDTO;

public interface FollowCommandService {

    Follow createFollow(FollowRequestDTO.CreateFollowDTO request);

    void deleteFollow(Long followId);
}
