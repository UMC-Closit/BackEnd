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
    _NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "찾을 수 없습니다."),

    // 사용자 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "LOGIN4001", "사용자가 존재하지 않습니다."),

    // 게시글 관련 에러
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST4001", "게시글이 존재하지 않습니다."),

    //북마크 관련 에러
    BOOKMARK_NOT_FOUND(HttpStatus.NOT_FOUND,"BOOLMARK4001","북마크가 존재하지 않습니다."),

    //좋아요 관련 에러
    LIKES_NOT_FOUND(HttpStatus.BAD_REQUEST, "LIKE4001", "좋아요가 존재하지 않습니다."),
    LIKES_ALREADY_EXIST (HttpStatus.BAD_REQUEST, "LIKE4002", "이미 좋아요를 누른 게시글 입니다."),


    // 댓글 관련 에러
    COMMENT_NOT_FOUND (HttpStatus.NOT_FOUND, "COMMENT4001", "댓글이 존재하지 않습니다."),

    // 배틀 관련 에러
    BATTLE_NOT_FOUND (HttpStatus.BAD_REQUEST, "BATTLE4001", "배틀이 존재하지 않습니다."),
    BATTLE_NOT_CHALLENGE (HttpStatus.BAD_REQUEST, "BATTLE4002", "동일한 게시글로 배틀을 신청할 수 없습니다."),
    BATTLE_ALREADY_EXIST (HttpStatus.BAD_REQUEST, "BATTLE4003", "배틀이 이미 존재합니다."),
    POST_NOT_BATTLE (HttpStatus.BAD_REQUEST, "BATTLE4004", "해당 게시글은 배틀이 아닙니다"),
    POST_ALREADY_BATTLE (HttpStatus.BAD_REQUEST, "BATTLE4005", "해당 게시글은 이미 배틀 게시글입니다."),
    POST_IS_CHALLENGE (HttpStatus.BAD_REQUEST, "BATTLE4006", "해당 게시글은 배틀 챌린지 게시글입니다."),

    // 투표 관련 에러
    VOTE_ALREADY_EXIST (HttpStatus.BAD_REQUEST, "VOTE4001", "이미 투표를 했습니다."),
    VOTE_EXPIRED (HttpStatus.BAD_REQUEST, "VOTE4002", "이미 종료된 투표 입니다."),

    // 배틀 좋아요 관련 에러
    BATTLE_LIKES_ALREADY_EXIST (HttpStatus.BAD_REQUEST, "BATTLELIKE4001", "이미 좋아요를 누른 배틀 입니다."),
    BATTLE_LIKES_NOT_FOUND (HttpStatus.BAD_REQUEST, "BATTLELIKE4002", "배틀 좋아요가 존재하지 않습니다"),

    // 배틀 댓글 관련 에러
    BATTLE_COMMENT_NOT_FOUND (HttpStatus.BAD_REQUEST, "BATTLECOMMENT4001", "배틀 댓글이 존재하지 않습니다."),

    // 하이라이트 관련 에러
    HIGHLIGHT_NOT_FOUND(HttpStatus.NOT_FOUND, "HIGHLIGHT4041", "하이라이트가 존재하지 않습니다."),

    // 하이라이트 게시글 관련 에러
    HIGHLIGHT_POST_NOT_FOUND(HttpStatus.NOT_FOUND, "HIGHLIGHTPOST4041", "하이라이트 게시글이 존재하지 않습니다."),

    // 팔로우 관련 에러
    FOLLOW_NOT_FOUND(HttpStatus.NOT_FOUND, "FOLLOW4041", "팔로우가 존재하지 않습니다."),

    // 미션 관련 에러
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4041", "미션이 존재하지 않습니다.");

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
