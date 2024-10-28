package land.leets.Carrot.domain.career.repository;

import java.util.Optional;
import land.leets.Carrot.domain.career.entity.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {
    public Optional<WorkType> findByType(String typeName);
}
