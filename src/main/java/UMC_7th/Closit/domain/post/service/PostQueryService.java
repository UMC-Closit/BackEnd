package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.dto.PostRequestDTO;
import UMC_7th.Closit.domain.post.entity.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostQueryService {

    // 팔로우 기반 게시글 조회
    Slice<Post> getPostListByFollowing(boolean follower, Pageable pageable);
    
    // 해시태그 기반 게시글 검색
    Slice<Post> getPostListByHashtag(String hashtag, Pageable pageable);
}

