package Employee_Management.Employee_Management.Repository;

import Employee_Management.Employee_Management.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
