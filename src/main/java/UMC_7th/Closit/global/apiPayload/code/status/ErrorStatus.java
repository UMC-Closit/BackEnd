package UMC_7th.Closit.global.apiPayload.code.status;

import UMC_7th.Closit.global.apiPayload.code.BaseErrorCode;
import UMC_7th.Closit.global.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 사용자 관련 에러
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "LOGIN4004", "사용자를 찾을 수 없습니다."),

    // 게시글 관련 에러
    POST_NOT_FOUND(HttpStatus.BAD_REQUEST, "POST4001", "게시글이 존재하지 않습니다."),

    // 배틀 관련 에러
    BATTLE_NOT_FOUND (HttpStatus.BAD_REQUEST, "BATTLE4001", "배틀이 존재하지 않습니다."),
    BATTLE_NOT_CHALLENGE (HttpStatus.BAD_REQUEST, "BATTLE4002", "동일한 게시글로 배틀을 신청할 수 없습니다"),
    BATTLE_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "BATTLE4003", "배틀이 이미 존재합니다."),
    POST_IS_NOT_BATTLE(HttpStatus.BAD_REQUEST, "BATTLE4004", "해당 게시글은 배틀이 아닙니다"),
    VOTE_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "VOTE4005", "이미 투표를 했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
