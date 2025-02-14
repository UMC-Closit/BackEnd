package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserQueryService {

    Slice<Highlight> getHighlightList(String clositId, Pageable pageable);

    Slice<User> getFollowerList(String clositId, Pageable pageable);

    Slice<User> getFollowingList(String clositId, Pageable pageable);

    User getUserInfo(String clositId);

}
