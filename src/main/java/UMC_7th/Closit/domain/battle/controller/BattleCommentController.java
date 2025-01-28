package UMC_7th.Closit.domain.battle.controller;

import UMC_7th.Closit.domain.battle.converter.BattleCommentConverter;
import UMC_7th.Closit.domain.battle.dto.BattleCommentDTO.BattleCommentRequestDTO;
import UMC_7th.Closit.domain.battle.dto.BattleCommentDTO.BattleCommentResponseDTO;
import UMC_7th.Closit.domain.battle.entity.BattleComment;
import UMC_7th.Closit.domain.battle.service.BattleCommentService.BattleCmtCommandService;
import UMC_7th.Closit.domain.battle.service.BattleCommentService.BattleCmtQueryService;
import UMC_7th.Closit.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/communities/battle")
public class BattleCommentController {

    private final BattleCmtCommandService battleCmtCommandService;
    private final BattleCmtQueryService battleCmtQueryService;

    @Operation(summary = "새로운 배틀 댓글 생성")
    @PostMapping("/{battle_id}/comments")
    public ApiResponse<BattleCommentResponseDTO.createBattleCommentResultDTO> createBattleComment(@RequestBody @Valid BattleCommentRequestDTO.createBattleCommentRequestDTO request,
                                                                                                  @PathVariable("battle_id") Long battleId) {

        BattleComment battleComment = battleCmtCommandService.createBattleComment(battleId, request);

        return ApiResponse.onSuccess(BattleCommentConverter.createBattleCommentResponseDTO(battleComment));
    }

    @Operation(summary = "배틀 댓글 조회")
    @GetMapping("/{battle_id}/comments")
    public ApiResponse<BattleCommentResponseDTO.BattleCommentPreviewListDTO> getBattleComments(@PathVariable("battle_id") Long battleId,
                                                                                               @RequestParam(name = "page") Integer page) {

        Slice<BattleComment> battleCommentList = battleCmtQueryService.getBattleCommentList(battleId, page);

        return ApiResponse.onSuccess(BattleCommentConverter.battleCommentPreviewListDTO(battleCommentList));
    }

    @Operation(summary = "배틀 댓글 삭제")
    @DeleteMapping("/{battle_id}/comments/{battle_comment_id}")
    public ApiResponse<String> deleteBattleComment(@PathVariable("battle_id") Long battleId,
                                                   @PathVariable("battle_comment_id") Long battleCommentId) {

        battleCmtCommandService.deleteBattleComment(battleId, battleCommentId);

        return ApiResponse.onSuccess("Deletion successful");
    }
}