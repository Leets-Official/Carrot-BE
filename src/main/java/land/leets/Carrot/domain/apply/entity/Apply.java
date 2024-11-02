package land.leets.Carrot.domain.apply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    private long postId;

    private LocalDateTime viewAt;

    private boolean isRecruited = false;

    private boolean isResponsed = false;

    @Builder
    public Apply(Long postId,
                 Long userId){
        this.postId = postId;
        this.userId = userId;
    }
}
