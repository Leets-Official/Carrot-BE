package land.leets.Carrot.domain.review.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import land.leets.Carrot.domain.review.entity.enums.WorkType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long applyId;

    private String review;

    private boolean isRecommend;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private WorkType worktype;
}
