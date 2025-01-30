package UMC_7th.Closit.domain.user.dto;

import UMC_7th.Closit.domain.follow.dto.FollowResponseDTO;
import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class UserResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDTO {
        private Long userId;
        private String clositId;
        private String name;
        private String email;
        private LocalDate birth;
        private String profileImage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserHighlightListDTO {
        private List<HighlightResponseDTO.HighlightDTO> highlightList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserFollwerListDTO {
        private List<UserDTO> followerList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserFollwingListDTO {
        private List<UserDTO> followingList;
    }
}
