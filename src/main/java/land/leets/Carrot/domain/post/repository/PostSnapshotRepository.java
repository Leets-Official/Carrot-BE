package land.leets.Carrot.domain.post.repository;

import java.util.List;
import java.util.Optional;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostSnapshotRepository extends JpaRepository<PostSnapshot, Long> {
    @Override
    Optional<PostSnapshot> findById(Long aLong);

    Optional<PostSnapshot> findByPostIdAndIsLastestTrue(Long postId);

    @Query("SELECT postsnapshot from PostSnapshot postsnapshot WHERE postsnapshot.title LIKE %:keyword%")
    Optional<List<PostSnapshot>> findByKeywordAndIsLastestTrue(@Param("keyword") String keyword);

}
