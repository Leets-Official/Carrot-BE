package land.leets.Carrot.domain.post.repository;

import java.util.List;
import java.util.Optional;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostSnapshotRepository extends JpaRepository<PostSnapshot, Long> {

    @Query("SELECT postSnapshot FROM PostSnapshot postSnapshot WHERE postSnapshot.post.postId = :postId")
    Optional<PostSnapshot> findByPostIdAndIsLastestTrue(Long postId);

    @Query("SELECT postSnapshot from PostSnapshot postSnapshot WHERE postSnapshot.title LIKE %:keyword% AND postSnapshot.isLastest = true ")
    Optional<List<PostSnapshot>> findByKeywordAndIsLastestTrue(@Param("keyword") String keyword);

}
