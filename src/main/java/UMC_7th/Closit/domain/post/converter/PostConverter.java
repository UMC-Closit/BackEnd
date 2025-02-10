package UMC_7th.Closit.domain.post.converter;

import UMC_7th.Closit.domain.post.dto.PostRequestDTO;
import UMC_7th.Closit.domain.post.dto.PostResponseDTO;
import UMC_7th.Closit.domain.post.entity.ItemTag;
import UMC_7th.Closit.domain.post.entity.Post;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class PostConverter {

    public static PostResponseDTO.PostPreviewDTO toPostPreviewDTO(Post post, Boolean isLiked, Boolean isSaved, Boolean isFriend,
                                                                  List<String> hashtags, List<ItemTag> frontTags, List<ItemTag> backTags) {
        List<PostResponseDTO.ItemTagDTO> frontItemtags = frontTags.stream()
                .map(tag -> PostResponseDTO.ItemTagDTO.builder()
                        .x(tag.getItemTagX())
                        .y(tag.getItemTagY())
                        .build())
                .collect(Collectors.toList());

        List<PostResponseDTO.ItemTagDTO> backItemtags = backTags.stream()
                .map(tag -> PostResponseDTO.ItemTagDTO.builder()
                        .x(tag.getItemTagX())
                        .y(tag.getItemTagY())
                        .build())
                .collect(Collectors.toList());
        return PostResponseDTO.PostPreviewDTO.builder()
                .postId(post.getId())
                .userId(post.getUser().getId())
                .profileImage(post.getUser().getProfileImage())
                .frontImage(post.getFrontImage())
                .backImage(post.getBackImage())
                .isLiked(isLiked)
                .isSaved(isSaved)
                .isFriend(isFriend)
                .hashtags(hashtags)
                .frontItemtags(frontItemtags)
                .backItemtags(backItemtags)
                .pointColor(post.getPointColor())
                .visibility(post.getVisibility())
                .build();

    }

    public static PostResponseDTO.PostPreviewListDTO toPostPreviewListDTO(Slice<Post> posts) {
        List<PostResponseDTO.PostPreviewDTO> postPreviewList = posts.stream()
                .map(post -> {
                    return toPostPreviewDTO(post, false, false, false, List.of(), List.of(), List.of());
                })
                .collect(Collectors.toList());

        return PostResponseDTO.PostPreviewListDTO.builder()
                .postPreviewList(postPreviewList)
                .listSize(postPreviewList.size())
                .isFirst(posts.isFirst())
                .isLast(posts.isLast())
                .hasNext(posts.hasNext())
                .build();
    }

    public static PostRequestDTO.CreatePostDTO toPost(Post post) {
        return PostRequestDTO.CreatePostDTO.builder()
                .userId(post.getUser().getId())               // User ID
                .frontImage(post.getFrontImage())             // 앞면 이미지
                .backImage(post.getBackImage())              // 뒷면 이미지
                .pointColor(post.getPointColor())             // 포인트 컬러
                .visibility(post.getVisibility())             // 공개 여부
                .hashtags(post.getPostHashtagList().stream()  // 해시태그
                        .map(postHashTag -> postHashTag.getHashTag().getContent())
                        .collect(Collectors.toList()))
                .frontItemtags(post.getItemTagList().stream() // Front ItemTags
                        .filter(itemTag -> "FRONT".equals(itemTag.getTagType()))
                        .map(itemTag -> PostResponseDTO.ItemTagDTO.builder()
                                .x(itemTag.getItemTagX())
                                .y(itemTag.getItemTagY())
                                .build())
                        .collect(Collectors.toList()))
                .backItemtags(post.getItemTagList().stream()  // Back ItemTags
                        .filter(itemTag -> "BACK".equals(itemTag.getTagType()))
                        .map(itemTag -> PostResponseDTO.ItemTagDTO.builder()
                                .x(itemTag.getItemTagX())
                                .y(itemTag.getItemTagY())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

}

