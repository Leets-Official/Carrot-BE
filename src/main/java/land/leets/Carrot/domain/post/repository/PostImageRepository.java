package land.leets.Carrot.domain.post.repository;

import java.util.List;
import land.leets.Carrot.domain.post.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
    @Query("SELECT p FROM PostImage p WHERE p.postSnapshot.id = :postsnapshotId")
    List<PostImage> findByPostSnapshotId(@Param("postsnapshotId") Long postsnapshotId);
}
