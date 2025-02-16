package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.dto.PostResponseDTO;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostQueryService {

    PostResponseDTO.PostPreviewDTO getPostById(Long postId, User currentUser);

    // 팔로우 기반 게시글 조회
    Slice<PostResponseDTO.PostPreviewDTO> getPostListByFollowing(boolean follower, Pageable pageable);
    
    // 해시태그 기반 게시글 검색
    Slice<PostResponseDTO.PostPreviewDTO> getPostListByHashtag(String hashtag, Pageable pageable);
}

