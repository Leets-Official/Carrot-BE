package land.leets.Carrot.domain.post.repository;

import java.util.Optional;
import land.leets.Carrot.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    Optional<Post> findById(Long aLong);

}
