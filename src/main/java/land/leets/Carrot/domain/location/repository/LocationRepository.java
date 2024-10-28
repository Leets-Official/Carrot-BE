package land.leets.Carrot.domain.location.repository;

import java.util.Optional;
import land.leets.Carrot.domain.location.entity.DetailArea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<DetailArea, Integer> {
    public Optional<DetailArea> findByName(String name);
}
