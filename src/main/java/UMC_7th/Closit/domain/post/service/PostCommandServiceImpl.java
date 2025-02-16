package UMC_7th.Closit.domain.post.service;

import UMC_7th.Closit.domain.post.dto.PostRequestDTO;
import UMC_7th.Closit.domain.post.entity.Hashtag;
import UMC_7th.Closit.domain.post.entity.ItemTag;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.entity.PostHashtag;
import UMC_7th.Closit.domain.post.repository.HashTagRepository;
import UMC_7th.Closit.domain.post.repository.ItemTagRepository;
import UMC_7th.Closit.domain.post.repository.PostHashTagRepository;
import UMC_7th.Closit.domain.post.repository.PostRepository;
import UMC_7th.Closit.domain.user.entity.User;
import UMC_7th.Closit.domain.user.repository.UserRepository;
import UMC_7th.Closit.global.apiPayload.code.status.ErrorStatus;
import UMC_7th.Closit.global.apiPayload.exception.GeneralException;
import UMC_7th.Closit.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostCommandServiceImpl implements PostCommandService {

    private final PostRepository postRepository;
    private final HashTagRepository hashtagRepository;
    private final PostHashTagRepository postHashtagRepository;
    private final ItemTagRepository itemTagRepository;
    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;

    @Override
    public Post createPost(PostRequestDTO.CreatePostDTO request) {
        // 1. User 조회

        User currentUser = securityUtil.getCurrentUser();

        // 2. Post 생성 및 저장
        Post post = Post.builder()
                .frontImage(request.getFrontImage())
                .backImage(request.getBackImage())
                .pointColor(request.getPointColor())
                .visibility(request.getVisibility())
                .user(currentUser)
                .build();

        postRepository.save(post);

        // 3. 해시태그 처리
        List<PostHashtag> postHashtags = request.getHashtags().stream()
                .map(tagContent -> {
                    Hashtag hashTag = hashtagRepository.findByContent(tagContent)
                            .orElseGet(() -> hashtagRepository.save(Hashtag.builder().content(tagContent).build()));
                    return PostHashtag.builder().post(post).hashtag(hashTag).build();
                })
                .collect(Collectors.toList());
        postHashtagRepository.saveAll(postHashtags);

        // 4. Front ItemTags 처리
        List<ItemTag> frontItemTags = request.getFrontItemtags().stream()
                .map(itemTagDTO -> ItemTag.builder()
                        .post(post)
                        .itemTagX(itemTagDTO.getX())
                        .itemTagY(itemTagDTO.getY())
                        .itemTagContent(itemTagDTO.getContent())
                        .tagType("FRONT")
                        .build())
                .collect(Collectors.toList());
        itemTagRepository.saveAll(frontItemTags);

        // 5. Back ItemTags 처리
        List<ItemTag> backItemTags = request.getBackItemtags().stream()
                .map(itemTagDTO -> ItemTag.builder()
                        .post(post)
                        .itemTagX(itemTagDTO.getX())
                        .itemTagY(itemTagDTO.getY())
                        .itemTagContent(itemTagDTO.getContent())
                        .tagType("BACK")
                        .build())
                .collect(Collectors.toList());
        itemTagRepository.saveAll(backItemTags);

        return post;
    }

    @Override
    public Post updatePost(Long postId, PostRequestDTO.UpdatePostDTO request) {
        // 1. 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        // 2. 게시글 정보 업데이트
        post.setFrontImage(request.getFrontImage());
        post.setBackImage(request.getBackImage());
        post.setPointColor(request.getPointColor());
        post.setVisibility(request.getVisibility());

        // 3. 기존 해시태그 삭제 후 새로운 해시태그 추가
        post.getPostHashtagList().clear();  // 리스트를 비움
        List<PostHashtag> newPostHashtags = request.getHashtags().stream()
                .map(tagContent -> {
                    Hashtag hashTag = hashtagRepository.findByContent(tagContent)
                            .orElseGet(() -> hashtagRepository.save(Hashtag.builder().content(tagContent).build()));
                    return PostHashtag.builder().post(post).hashtag(hashTag).build();
                })
                .collect(Collectors.toList());
        post.getPostHashtagList().addAll(newPostHashtags); // 새로운 태그 추가

        // 4. 기존 아이템 태그 삭제 후 새로운 태그 추가
        post.getItemTagList().clear();
        List<ItemTag> newItemTags = new ArrayList<>();
        newItemTags.addAll(request.getFrontItemtags().stream()
                .map(itemTagDTO -> ItemTag.builder()
                        .post(post)
                        .itemTagX(itemTagDTO.getX())
                        .itemTagY(itemTagDTO.getY())
                        .itemTagContent(itemTagDTO.getContent())
                        .tagType("FRONT")
                        .build())
                .collect(Collectors.toList()));

        newItemTags.addAll(request.getBackItemtags().stream()
                .map(itemTagDTO -> ItemTag.builder()
                        .post(post)
                        .itemTagX(itemTagDTO.getX())
                        .itemTagY(itemTagDTO.getY())
                        .itemTagContent(itemTagDTO.getContent())
                        .tagType("BACK")
                        .build())
                .collect(Collectors.toList()));
        post.getItemTagList().addAll(newItemTags);

        // 변경된 post 객체를 저장
        Post savedPost = postRepository.save(post);

        return savedPost;
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        postRepository.delete(post);
    }
}

