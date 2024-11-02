package land.leets.Carrot.domain.location.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//도,시,동 엔티티 테이블 하나로 합치는 방향으로 설계 변경

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer areaId;

    @Column(unique = true)
    private String name;

    @Builder
    public DetailArea(String doName) {
        this.name = doName;
    }

}
