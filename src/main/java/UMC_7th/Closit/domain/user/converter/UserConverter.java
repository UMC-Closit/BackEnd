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

    public static UserResponseDTO.UserHighlightListDTO toUserHighlightListDTO(Slice<Highlight> highlightList) {
        List<HighlightResponseDTO.HighlightDTO> highlightDTOs = highlightList.stream()
                .map(HighlightConverter::toHighlightDTO)
                .collect(Collectors.toList());

        return UserResponseDTO.UserHighlightListDTO.builder()
                .highlightList(highlightDTOs)
                .build();
    }

    public static UserResponseDTO.UserInfoDTO toUserInfoDTO(User user) {
        
        return UserResponseDTO.UserInfoDTO.builder()
                .id(user.getId())
                .clositId(user.getClositId())
                .name(user.getName())
                .email(user.getEmail())
                .birth(user.getBirth())
                .profileImage(user.getProfileImage())
                .build();
    }
}
