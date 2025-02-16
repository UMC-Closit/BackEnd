package UMC_7th.Closit.domain.user.service;

import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface UserQueryService {

    Slice<Highlight> getHighlightList(String clositId, Pageable pageable);

    Slice<User> getFollowerList(String clositId, Pageable pageable);

    Slice<User> getFollowingList(String clositId, Pageable pageable);

    User getUserInfo(String clositId);

    boolean isMissionDone();

    Slice<Post> getRecentPostList(String clositId, Integer page); // 특정 사용자의 최근 게시글 조회
}
