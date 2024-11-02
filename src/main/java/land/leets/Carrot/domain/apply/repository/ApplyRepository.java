package land.leets.Carrot.domain.apply.repository;

import land.leets.Carrot.domain.apply.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
}
