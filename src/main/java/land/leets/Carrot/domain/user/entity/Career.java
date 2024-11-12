package land.leets.Carrot.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String workplace;
    private String workType;
    private String workYear;
    private String workPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Employee employee;

    public Career(String workplace, String workType, String workYear, String workPeriod, Employee employee) {
        this.workplace = workplace;
        this.workType = workType;
        this.workYear = workYear;
        this.workPeriod = workPeriod;
        this.employee = employee;
    }

    public void update(String workplace, String workType, String workYear, String workPeriod) {
        this.workplace = workplace;
        this.workType = workType;
        this.workYear = workYear;
        this.workPeriod = workPeriod;
    }
}
