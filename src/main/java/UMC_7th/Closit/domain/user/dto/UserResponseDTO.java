package UMC_7th.Closit.domain.user.dto;

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
    public static class UserHighlightListDTO {
        private List<HighlightResponseDTO.HighlightDTO> highlightList;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfoDTO {
        private Long id;                // User ID
        private String clositId;        // 사용자 닉네임
        private String name;            // 사용자 이름
        private String email;           // 이메일
        private LocalDate birth;        // 생년월일
        private String profileImage;    // 프로필 이미지
    }

}
