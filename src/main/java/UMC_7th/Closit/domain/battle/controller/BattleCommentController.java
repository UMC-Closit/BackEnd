package UMC_7th.Closit.domain.battle.controller;

import UMC_7th.Closit.domain.battle.converter.BattleCommentConverter;
import UMC_7th.Closit.domain.battle.dto.battleCommentDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.dto.battleCommentDTO.BattleCommentResponseDTO;
import UMC_7th.Closit.domain.battle.entity.BattleComment;
import UMC_7th.Closit.domain.battle.service.battleCommentService.BattleCommentService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/communities/battle")
public class BattleCommentController {

    private final BattleCommentService battleCommentService;

    @Operation(summary = "새로운 배틀 댓글 생성")
    @PostMapping("/{battle_id}/comments")
    public ApiResponse<BattleCommentResponseDTO.createBattleCommentResultDTO> createBattleComment(@RequestBody @Valid BattleCommentRequestDTO.createBattleCommentRequestDTO request,
                                                                                                  @PathVariable("battle_id") Long battleId) {

        BattleComment battleComment = battleCommentService.createBattleComment(battleId, request);

        return ApiResponse.onSuccess(BattleCommentConverter.createBattleCommentResponseDTO(battleComment));
    }
}