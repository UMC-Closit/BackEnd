package UMC_7th.Closit.domain.user.dto;

import UMC_7th.Closit.domain.follow.dto.FollowResponseDTO;
import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import UMC_7th.Closit.domain.user.converter.UserConverter;
import UMC_7th.Closit.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Slice;

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
    public static class UserHighlightSliceDTO {
        private List<HighlightResponseDTO.HighlightDTO> highlights;
        private boolean hasNext;
        private int pageNumber;
        private int size;

        public static UserHighlightSliceDTO from(Slice<HighlightResponseDTO.HighlightDTO> slice) {
            return UserHighlightSliceDTO.builder()
                    .highlights(slice.getContent())
                    .hasNext(slice.hasNext())
                    .pageNumber(slice.getNumber())
                    .size(slice.getSize())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserFollowerSliceDTO {
        private List<UserDTO> followers;
        private boolean hasNext;
        private int pageNumber;
        private int size;

        public static UserFollowerSliceDTO from(Slice<User> slice) {
            return UserFollowerSliceDTO.builder()
                    .followers(slice.map(UserConverter::toUserDTO).getContent())
                    .hasNext(slice.hasNext())
                    .pageNumber(slice.getNumber())
                    .size(slice.getSize())
                    .build();
        }
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserFollowingSliceDTO {
        private List<UserDTO> following;
        private boolean hasNext;
        private int pageNumber;
        private int size;

        public static UserFollowingSliceDTO from(Slice<User> slice) {
            return UserFollowingSliceDTO.builder()
                    .following(slice.map(UserConverter::toUserDTO).getContent())
                    .hasNext(slice.hasNext())
                    .pageNumber(slice.getNumber())
                    .size(slice.getSize())
                    .build();
        }
    }
}
