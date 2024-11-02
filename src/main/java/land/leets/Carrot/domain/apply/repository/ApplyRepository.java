package land.leets.Carrot.domain.apply.repository;

import java.util.List;
import java.util.Optional;
import land.leets.Carrot.domain.apply.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    @Query("SELECT apply FROM Apply apply where apply.postId = :postId AND apply.userId = :userId")
    Optional<Apply> findByPostIdAndUserID (@Param("postId") Long postId, @Param("userId") Long userId);

    @Query("SELECT apply FROM Apply apply where apply.postId =:postId")
    List<Apply> findByPostId(@Param("postId") Long postId);
}
