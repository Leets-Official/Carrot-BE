package land.leets.Carrot.domain.review.entity;

import jakarta.persistence.*;
import land.leets.Carrot.domain.review.entity.enums.WorkType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long applyId;

    private String review;

    private boolean isRecommend;

    private Timestamp createAt;

    private Timestamp updateAt;

    private WorkType worktype;
}
