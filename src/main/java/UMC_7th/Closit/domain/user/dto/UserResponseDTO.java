package UMC_7th.Closit.domain.user.dto;

import UMC_7th.Closit.domain.highlight.dto.HighlightResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class UserResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserHighlightListDTO {
        private List<HighlightResponseDTO.HighlightDTO> highlightList;
    }
}
