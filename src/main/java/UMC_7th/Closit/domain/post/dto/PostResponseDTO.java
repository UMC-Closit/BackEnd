package UMC_7th.Closit.domain.post.dto;

import UMC_7th.Closit.domain.post.entity.Visibility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PostResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostPreviewDTO { // 게시글 목록 조회
        private Long userId;
        private String profileImage;
        private String frontImage;
        private String backImage;
        private Boolean isLiked;
        private Boolean isSaved;
        private Boolean isFriend;
        private List<String> hashtags;
        private List<ItemTagDTO> frontItemtags;
        private List<ItemTagDTO> backItemtags;
        private String pointColor;
        private Visibility visibility;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ItemTagDTO {
        private Double x; // X 좌표
        private Double y; // Y 좌표
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostPreviewListDTO {
        private List<PostPreviewDTO> postPreviewList;
        private Integer listSize;
        private boolean isFirst;
        private boolean isLast;
        private boolean hasNext;
    }


}
