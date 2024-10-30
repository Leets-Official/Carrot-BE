package land.leets.Carrot.domain.post.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    GET_POST_DETAIL_SUCCESS(200, "세부 게시글 데이터 조회 성공.");

    private final int code;
    private final String message;

}
