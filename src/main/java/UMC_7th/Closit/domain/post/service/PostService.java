package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.follow.repository.FollowRepository;
import UMC_7th.Closit.domain.post.converter.PostConverter;
import UMC_7th.Closit.domain.post.dto.PostResponseDTO;
import UMC_7th.Closit.domain.post.entity.ItemTag;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.repository.*;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikesRepository likesRepository;
    private final BookmarkRepository bookmarkRepository;
    private final FollowRepository followRepository;
    private final PostHashtagRepository postHashTagRepository;
    private final ItemTagRepository itemTagRepository;

    public PostResponseDTO.PostPreviewDTO getPostById(Long postId, User currentUser) {
        // 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._NOT_FOUND));

        // 좋아요 여부 확인
        Boolean isLiked = likesRepository.existsByUserAndPost(currentUser, post);

        // 북마크 여부 확인
        Boolean isSaved = bookmarkRepository.existsByUserAndPost(currentUser, post);

        // 친구 여부 확인
        Boolean isFriend = followRepository.existsByFollowerAndFollowing(currentUser, post.getUser()) &&
                followRepository.existsByFollowerAndFollowing(post.getUser(), currentUser);

        // 해시태그 조회
        List<String> hashtags = postHashTagRepository.findByPost(post).stream()
                .map(postHashtag -> postHashtag.getHashtag().getContent())
                .collect(Collectors.toList());

        // 아이템 태그 조회
        List<ItemTag> frontTags = itemTagRepository.findByPostAndTagType(post, "FRONT");
        List<ItemTag> backTags = itemTagRepository.findByPostAndTagType(post, "BACK");

        // DTO로 변환
        return PostConverter.toPostPreviewDTO(post, isLiked, isSaved, isFriend, hashtags, frontTags, backTags);
    }
}

