package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostQueryService {

    Slice<Post> getPostListByFollowerAndHashtag(Long userId, boolean follower, String hashtag, Pageable pageable);
}

