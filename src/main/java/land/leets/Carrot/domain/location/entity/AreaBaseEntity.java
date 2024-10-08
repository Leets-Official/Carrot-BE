package land.leets.Carrot.domain.location.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public abstract class AreaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;

    private String name;
}
