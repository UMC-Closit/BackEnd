package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserQueryService {

    Slice<Highlight> getHighlightList(Long userId, Pageable pageable);

    Slice<User> getFollowerList(Long userId, Pageable pageable);

    Slice<User> getFollowingList(Long userId, Pageable pageable);

    Slice<Mission> getMissionList(Long userId, Pageable pageable);

    User getUserInfo(Long userId);
}
