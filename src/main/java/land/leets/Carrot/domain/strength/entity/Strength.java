package land.leets.Carrot.domain.strength.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Strength {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String strengthName;
}
