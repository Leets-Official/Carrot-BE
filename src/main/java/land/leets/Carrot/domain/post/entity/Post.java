package land.leets.Carrot.domain.post.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;
import land.leets.Carrot.domain.apply.entity.Apply;
import land.leets.Carrot.domain.user.entity.Ceo;
import land.leets.Carrot.global.common.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private long postId;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<PostSnapshot> postSnapshot;

    @ManyToOne
    @JoinColumn(name = "ceo_id", nullable = false)
    private Ceo ceo;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    //조회수 기능 mvp 이후 구현
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    //deleted, recruiting, done, illegal
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private Set<Apply> apply;

    @Builder
    public Post(Ceo ceo, String storeName, LocalDateTime createAt, String status) {
        this.ceo = ceo;
        this.storeName = storeName;
        this.createAt = createAt;
        this.status = status;
    }
}
