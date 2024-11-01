package land.leets.Carrot.domain.post.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {

    GET_POST_DETAIL_SUCCESS(HttpStatus.OK.value(), "세부 게시글 데이터 조회 성공."),
    GET_POSTED_POST_LIST_SUCCESS(HttpStatus.OK.value(), "유저가 작성한 게시글 조회 성공."),
    GET_POST_KEYWORD_SEARCH_SUCCESS(HttpStatus.OK.value(), "키워드를 제목에 포함하는 게시글 조회 성공."),
    GET_POST_LIST_SHORT_DATA_VER(HttpStatus.OK.value(), "간략한 버전의 게시글 리스트 조회 성공."),
    GET_WORK_TYPE_LIST_SUCCESS(HttpStatus.OK.value(), "존재하는 업무 리스트 조회 성공");

    private final int code;
    private final String message;

}
