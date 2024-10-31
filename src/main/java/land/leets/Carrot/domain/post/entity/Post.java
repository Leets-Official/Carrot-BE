package land.leets.Carrot.domain.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import land.leets.Carrot.global.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    //조회수 기능 mvp 이후 구현
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    //deleted, recruting, done, illegal
    @Column(name = "status", nullable = false)
    private String status;

    @Builder
    public Post(long userId, String storeName, LocalDateTime createAt, String status) {
        this.userId = userId;
        this.storeName = storeName;
        this.createAt = createAt;
        this.status = status;
    }
}
