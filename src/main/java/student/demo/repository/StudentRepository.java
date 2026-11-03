package student.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import student.demo.entity.Student;
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByDepartment(String department);
    List<Student> findByName(String name);
    List<Student> findByNameContaining(String name);
    boolean existsByEmail(String email);

}