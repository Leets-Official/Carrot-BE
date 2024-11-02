package land.leets.Carrot.domain.apply.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import land.leets.Carrot.domain.post.entity.Post;
import land.leets.Carrot.domain.user.entity.Employee;
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

    private LocalDateTime viewAt;

    private boolean isRecruited = false;

    private boolean isResponsed = false;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Builder
    public Apply(Post post,
                 Employee employee) {
        this.post = post;
        this.employee = employee;
    }

    public void setIsRecruited(boolean isRecruited) {
        this.isRecruited = isRecruited;
    }
}
