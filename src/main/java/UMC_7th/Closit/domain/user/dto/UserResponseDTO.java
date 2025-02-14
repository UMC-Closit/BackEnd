package UMC_7th.Closit.domain.user.dto;

import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import UMC_7th.Closit.domain.mission.converter.MissionConverter;
import UMC_7th.Closit.domain.mission.dto.MissionResponseDTO;
import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.user.converter.UserConverter;
import UMC_7th.Closit.domain.user.entity.Role;
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
        private List<UserDTO> followings;
        private boolean hasNext;
        private int pageNumber;
        private int size;

        public static UserFollowingSliceDTO from(Slice<User> slice) {
            return UserFollowingSliceDTO.builder()
                    .followings(slice.map(UserConverter::toUserDTO).getContent())
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
    public static class UserMissionSliceDTO {
        private List<MissionResponseDTO.MissionDTO> missions;
        private boolean hasNext;
        private int pageNumber;
        private int size;

        public static UserMissionSliceDTO from(Slice<Mission> slice) {
            return UserMissionSliceDTO.builder()
                    .missions(slice.map(MissionConverter::toMissionDTO).getContent())
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
    public static class UserInfoDTO {
        private Long id;                // User ID
        private Role role;              // USer role
        private String clositId;        // 사용자 닉네임
        private String name;            // 사용자 이름
        private String email;           // 이메일
        private LocalDate birth;        // 생년월일
        private String profileImage;    // 프로필 이미지
    }

}
