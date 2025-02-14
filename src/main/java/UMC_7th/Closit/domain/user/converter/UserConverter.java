package UMC_7th.Closit.domain.user.converter;

import UMC_7th.Closit.domain.highlight.converter.HighlightConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.mission.entity.Mission;
import UMC_7th.Closit.domain.user.dto.UserResponseDTO;
import UMC_7th.Closit.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserResponseDTO.UserDTO toUserDTO(User user) {
        return UserResponseDTO.UserDTO.builder()
                .clositId(user.getClositId())
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .profileImage(user.getProfileImage())
                .build();
    }

    public static UserResponseDTO.UserInfoDTO toUserInfoDTO(User user) {

        return UserResponseDTO.UserInfoDTO.builder()
                .id(user.getId())
                .role(user.getRole())
                .clositId(user.getClositId())
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .profileImage(user.getProfileImage())
                .build();
    }

    public static UserResponseDTO.UserHighlightSliceDTO toUserHighlightSliceDTO(@NotNull Slice<Highlight> highlightSlice) {
        Slice<HighlightResponseDTO.HighlightDTO> highlightDTOs = highlightSlice.map(HighlightConverter::toHighlightDTO);

        return UserResponseDTO.UserHighlightSliceDTO.builder()
                .highlights(highlightDTOs.getContent())
                .hasNext(highlightSlice.hasNext())
                .pageNumber(highlightSlice.getNumber())
                .size(highlightSlice.getSize())
                .build();
    }

    public static UserResponseDTO.UserFollowerSliceDTO toUserFollowerSliceDTO(Slice<User> followerSlice) {
        return UserResponseDTO.UserFollowerSliceDTO.from(followerSlice);
    }

    public static UserResponseDTO.UserFollowingSliceDTO toUserFollowingSliceDTO(Slice<User> followingSlice) {
        return UserResponseDTO.UserFollowingSliceDTO.from(followingSlice);
    }
}
