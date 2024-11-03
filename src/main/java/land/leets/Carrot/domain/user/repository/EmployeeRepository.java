package land.leets.Carrot.domain.user.repository;

import java.util.List;
import land.leets.Carrot.domain.user.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    List<Employee> findByIdIn(List<Long> userIds);
}
