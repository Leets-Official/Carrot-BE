package land.leets.Carrot.domain.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postId;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "store_name", nullable = false)
    private long storeName;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    //조회수 기능 mvp 이후 구현
    private Long view;

    @Column(name = "status", nullable = false)
    private String status;
}
