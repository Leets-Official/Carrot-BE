package land.leets.Carrot.domain.post.repository;

import java.util.List;
import java.util.Optional;
import land.leets.Carrot.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    Optional<Post> findById(Long aLong);

    @Query("SELECT post FROM Post post WHERE post.status=:recuiting")
    Optional<List<Post>> findByStatus(@Param("status") String status);
}
