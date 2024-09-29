package land.leets.Carrot.domain.location.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AreaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;

    private String name;
}
