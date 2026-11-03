package student.demo.Controller;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import student.demo.DTO.StudentDTO;
import student.demo.DTO.StudentRequestDTO;
import student.demo.entity.Student;
import student.demo.service.StudentService;
@Valid
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Add Student
    @PostMapping
    public Student addStudent(@Valid @RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // Get All Students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Search by Department
@GetMapping("/department/{department}")
public List<Student> getStudentsByDepartment(
        @PathVariable String department) {

    return studentService.searchStudentsByDepartment(department);
}

    // Get Student By ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    // Update Student
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @Valid @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully";
    }
@GetMapping("/pagination")
public Page<Student> getStudentsWithPagination(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    Pageable pageable = PageRequest.of(page, size, sort);

    return studentService.getStudentsWithPagination(pageable);
}
@GetMapping("/dto/{id}")
public StudentDTO getStudentDTO(@PathVariable int id) {
    return studentService.getStudentDTOById(id);
}
@PostMapping("/dto")
public Student addStudentFromDTO(@Valid @RequestBody StudentRequestDTO dto) {
    return studentService.addStudentFromDTO(dto);
}
// Update Student using DTO
@PutMapping("/dto/{id}")
public Student updateStudentFromDTO(
        @PathVariable int id,
        @Valid @RequestBody StudentRequestDTO dto) {

    return studentService.updateStudentFromDTO(id, dto);
}
@GetMapping("/name/{name}")
public List<Student> getStudentsByName(@PathVariable String name) {
    return studentService.searchStudentsByName(name);
}
@GetMapping("/search")
public List<Student> searchStudentsByPartialName(
        @RequestParam String name) {

    return studentService.searchStudentsByPartialName(name);
}
@GetMapping("/sort")
public List<Student> sortStudents(
        @RequestParam(defaultValue = "name") String sortBy,
        @RequestParam(defaultValue = "asc") String direction) {

    return studentService.sortStudents(sortBy, direction);
}
}