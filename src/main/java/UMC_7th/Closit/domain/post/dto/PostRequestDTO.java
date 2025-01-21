package UMC_7th.Closit.domain.post.dto;

import UMC_7th.Closit.domain.post.entity.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PostRequestDTO {
    @Getter
    @Builder
    public static class CreatePostDTO{
        private Long userId;
        private String frontImage;
        private String backImage;
        private List<String> hashtags;
        private List<PostResponseDTO.ItemTagDTO> frontItemtags;
        private List<PostResponseDTO.ItemTagDTO> backItemtags;
        private String pointColor;
        private Visibility visibility;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdatePostDTO {
        private String frontImage;
        private String backImage;
        private List<String> hashtags;
        private List<PostResponseDTO.ItemTagDTO> frontItemtags;
        private List<PostResponseDTO.ItemTagDTO> backItemtags;
        private String pointColor;
        private Visibility visibility;
    }
}
