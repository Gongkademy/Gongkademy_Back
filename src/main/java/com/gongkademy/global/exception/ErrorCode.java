package com.gongkademy.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    //일반
    //TODO: 수정이 필요함
    COMMON_ERROR(HttpStatus.BAD_REQUEST, "처리되지 않은 에러입니다."),

    //Member
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다."),
    INVALID_MEMBER_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 회원 아이디입니다."),

    //JWT
    JWT_MALFORMED(HttpStatus.UNAUTHORIZED, "손상된 토큰입니다."),
    JWT_HEADER_STRING(HttpStatus.UNAUTHORIZED, "토큰 헤더의 문자열이 이상합니다."),
    JWT_NULL_REFRESH(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 없습니다."),
    JWT_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
    JWT_INVALID(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    JWT_ERROR(HttpStatus.UNAUTHORIZED, "기타 JWT 에러"),

    //Board
    INVALID_BOARD_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 게시글 ID입니다."),

    //Comment
    INVALID_COMMENT_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 댓글 ID입니다."),
    INVALID_PARENT_COMMENT_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 부모 댓글 ID입니다."),

    //CommentLike
    INVALID_COMMENTLIKE_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 댓글 좋아요입니다."),

    //Pick
    INVALID_PICK_ID(HttpStatus.BAD_REQUEST, "유효하지 않은 픽입니다.");


    private final HttpStatus httpStatus;
    private final String message;

}
