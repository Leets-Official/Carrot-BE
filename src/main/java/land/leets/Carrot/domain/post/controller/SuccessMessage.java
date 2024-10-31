package land.leets.Carrot.domain.post.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    GET_POST_DETAIL_SUCCESS(200, "세부 게시글 데이터 조회 성공."),
    GET_POSTED_POST_LIST_SUCCESS(200, "유저가 작성한 게시글 조회 성공");

    private final int code;
    private final String message;

}
