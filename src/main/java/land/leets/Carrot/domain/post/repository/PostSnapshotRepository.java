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

    @Query("SELECT postsnapshot FROM PostSnapshot postsnapshot WHERE postsnapshot.post.postId = :postId and postsnapshot.isLastest = true")
    Optional<PostSnapshot> findByPostIdAndIsLastestTrue(Long postId);

    @Query("SELECT postSnapshot from PostSnapshot postSnapshot WHERE postSnapshot.title LIKE %:keyword% AND postSnapshot.isLastest = true ")
    Optional<List<PostSnapshot>> findByKeywordAndIsLastestTrue(@Param("keyword") String keyword);

    @Query("SELECT postsnapshot from PostSnapshot postsnapshot WHERE postsnapshot.post.postId IN :postIdList AND postsnapshot.isLastest = true")
    List<PostSnapshot> findByPostIdListAndIsLastest(@Param("postIdList") List<Long> postIdList);
}
