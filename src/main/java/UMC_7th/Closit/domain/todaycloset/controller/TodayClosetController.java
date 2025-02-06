package UMC_7th.Closit.domain.todaycloset.controller;

import UMC_7th.Closit.domain.todaycloset.dto.TodayClosetRequestDTO;
import UMC_7th.Closit.domain.todaycloset.dto.TodayClosetResponseDTO;
import UMC_7th.Closit.domain.todaycloset.service.TodayClosetService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/communities/todayclosets")
public class TodayClosetController {
    private final TodayClosetService todayClosetService;
    @PostMapping
    public ApiResponse<TodayClosetResponseDTO.CreateResponseDTO> createTodayCloset(@RequestBody TodayClosetRequestDTO.CreateRequestDTO request) {
        return ApiResponse.onSuccess(todayClosetService.createTodayCloset(request));
    }

    @GetMapping
    public ApiResponse<TodayClosetResponseDTO.TodayClosetListDTO> getTodayClosetList(@RequestParam(name = "page", defaultValue = "0") int page) {
        return ApiResponse.onSuccess(todayClosetService.getTodayClosetList(PageRequest.of(page, 10)));
    }

    @DeleteMapping("/{todayClosetId}")
    public ApiResponse<String> deleteTodayCloset(@PathVariable("todayClosetId") Long todayClosetId) {
        todayClosetService.deleteTodayCloset(todayClosetId);
        return ApiResponse.onSuccess("Deletion successful");
    }
}
