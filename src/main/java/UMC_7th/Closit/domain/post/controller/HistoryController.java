package UMC_7th.Closit.domain.post.controller;

import UMC_7th.Closit.domain.post.converter.HistoryConverter;
import UMC_7th.Closit.domain.post.dto.HistoryResponseDTO;
import UMC_7th.Closit.domain.post.entity.Post;
import UMC_7th.Closit.domain.post.service.HistoryQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/history")
public class HistoryController {

    private final HistoryQueryService historyQueryService;

    @GetMapping
    @Operation(summary = "사용자의 히스토리 조회 - 날짜",
            description = """
                    ## 사용자 날짜 별 히스토리 조회
                    ### Parameters
                    page [조회할 페이지 번호] - 0부터 시작, 31개씩 보여줌
                    """)
    public ApiResponse<HistoryResponseDTO.DataHistoryThumbnailListDTO> dateThumbnailList (@RequestParam(name = "page") Integer page) {

        Slice<Post> dateThumbnailList = historyQueryService.getHistoryThumbnailList(page);

        return ApiResponse.onSuccess(HistoryConverter.dataHistoryThumbnailListDTO(dateThumbnailList));
    }
}