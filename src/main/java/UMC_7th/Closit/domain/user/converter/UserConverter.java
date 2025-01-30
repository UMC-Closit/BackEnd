package UMC_7th.Closit.domain.user.converter;

import UMC_7th.Closit.domain.highlight.converter.HighlightConverter;
import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import UMC_7th.Closit.domain.highlight.entity.Highlight;
import UMC_7th.Closit.domain.user.dto.UserResponseDTO;
import UMC_7th.Closit.domain.user.entity.User;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    public static UserResponseDTO.UserDTO toUserDTO(User user) {
        return UserResponseDTO.UserDTO.builder()
                .userId(user.getId())
                .clositId(user.getClositId())
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .profileImage(user.getProfileImage())
                .build();
    }

    public static UserResponseDTO.UserHighlightListDTO toUserHighlightListDTO(Slice<Highlight> highlightList) {
        List<HighlightResponseDTO.HighlightDTO> highlightDTOs = highlightList.stream()
                .map(HighlightConverter::toHighlightDTO)
                .collect(Collectors.toList());

        return UserResponseDTO.UserHighlightListDTO.builder()
                .highlightList(highlightDTOs)
                .build();
    }

    public static UserResponseDTO.UserFollwerListDTO toUserFollwerListDTO(Slice<User> followerList) {
        List<UserResponseDTO.UserDTO> follwerDTOs = followerList.stream()
                .map(UserConverter::toUserDTO)
                .collect(Collectors.toList());

        return UserResponseDTO.UserFollwerListDTO.builder()
                .followerList(follwerDTOs)
                .build();
    }

    public static UserResponseDTO.UserFollwingListDTO toUserFollwingListDTO(Slice<User> followingList) {
        List<UserResponseDTO.UserDTO> follwingDTOs = followingList.stream()
                .map(UserConverter::toUserDTO)
                .collect(Collectors.toList());

        return UserResponseDTO.UserFollwingListDTO.builder()
                .followingList(follwingDTOs)
                .build();
    }
}
