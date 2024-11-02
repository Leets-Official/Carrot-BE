package land.leets.Carrot.domain.post.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PostErrorMessage {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 글을 찾을 수 없습니다."),
    LATEST_SNAPSHOT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 게시글의 최신 버전 스냅샷을 찾을 수 없습니다"),
    SEARCH_RESULT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 키워드를 포함하는 게시글이 없습니다.."),
    NO_RECRUITING_POST(HttpStatus.NOT_FOUND.value(), "현재 구인하는 구인게시글이 없습니다."),
    WROTE_POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "작성한 게시글이 없습니다."),

    WORK_TYPE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR.value(), "존재하는 업무 아이디의 업무내용을 찾을 수 없습니다."),
    LOCATION_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR.value(), "존재하는 지역 아이디의 지역명을 찾을 수 없습니다."),;
    private final Integer code;
    private final String errorMessage;
}
