package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UserQueryService {

    Slice<Highlight> getHighlightList(Long userId, Pageable pageable);

    Slice<User> getFollowerList(Long userId);

    Slice<User> getFollowingList(Long userId);
}
