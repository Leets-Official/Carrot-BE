package land.leets.Carrot.domain.user.repository;

import java.util.Optional;
import land.leets.Carrot.domain.user.entity.Ceo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CeoRepository extends JpaRepository<Ceo, Long> {
    boolean existsByEmail(String email);

    boolean existsByCeoPhoneNumber(String ceoPhoneNumber);

    boolean existsByCeoNumber(String ceoNumber);

    boolean existsByCeoName(String ceoName);

    @Query("SELECT ceo FROM Ceo ceo where ceo.id = :userId")
    Optional<Ceo> findByUserId(@Param("userId") Long userId);
}
