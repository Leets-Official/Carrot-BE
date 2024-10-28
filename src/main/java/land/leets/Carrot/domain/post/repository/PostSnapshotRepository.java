package land.leets.Carrot.domain.post.repository;

import java.util.Optional;
import land.leets.Carrot.domain.post.entity.PostSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostSnapshotRepository extends JpaRepository<PostSnapshot, Long> {
    @Override
    Optional<PostSnapshot> findById(Long aLong);

    PostSnapshot findByPostIdAndLastestTrue(Long postId);
}
