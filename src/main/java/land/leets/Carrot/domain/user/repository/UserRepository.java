package land.leets.Carrot.domain.user.repository;

import java.util.Optional;
import land.leets.Carrot.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByNickname(String nickname);

    Optional<User> findByEmail(String email);
}
