package land.leets.Carrot.domain.user.repository;

import land.leets.Carrot.domain.user.entity.Ceo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CeoRepository extends JpaRepository<Ceo, Long> {
    boolean existsByEmail(String email);

    boolean existsByCeoNumber(String ceoNumber);

    boolean existsByCeoName(String ceoName);

}
