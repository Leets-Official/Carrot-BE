package land.leets.Carrot.domain.post.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

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
    private Timestamp createAt;

    @Column(name = "category", nullable = false)
    private String category;

    //조회수 기능 mvp 이후 구현
    private Long view;

    @Column(name = "status", nullable = false)
    private String status;
}
