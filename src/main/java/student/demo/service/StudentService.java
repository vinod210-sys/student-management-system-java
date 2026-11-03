package student.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import student.demo.DTO.StudentDTO;
import student.demo.DTO.StudentRequestDTO;
import student.demo.entity.Student;
import student.demo.exception.DuplicateEmailException;
import student.demo.exception.StudentNotFoundException;
import student.demo.repository.StudentRepository;
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor Injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Add Student
   public Student addStudent(Student student) {

    if (studentRepository.existsByEmail(student.getEmail())) {
        throw new DuplicateEmailException(
        "Email already exists: " + student.getEmail());
    }

    return studentRepository.save(student);
}

    // Get All Students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get Student By ID
    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student with ID " + id + " not found"));
    }

    // Update Student
   public Student updateStudent(int id, Student newStudent) {

    Student existingStudent = getStudentById(id);

    if (!existingStudent.getEmail().equals(newStudent.getEmail())
            && studentRepository.existsByEmail(newStudent.getEmail())) {
       throw new DuplicateEmailException(
        "Email already exists: " + newStudent.getEmail());
    }

    existingStudent.setName(newStudent.getName());
    existingStudent.setEmail(newStudent.getEmail());
    existingStudent.setAge(newStudent.getAge());
    existingStudent.setDepartment(newStudent.getDepartment());

    return studentRepository.save(existingStudent);
}

    // Delete Student
    public void deleteStudent(int id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    // Search Students By Department
    public List<Student> searchStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department);
    }
    public Page<Student> getStudentsWithPagination(Pageable pageable) {
    return studentRepository.findAll(pageable);
}
public StudentDTO getStudentDTOById(int id) {

    Student student = getStudentById(id);

    return new StudentDTO(
        student.getId(),
        student.getName(),
        student.getDepartment()
    );
}
public Student addStudentFromDTO(StudentRequestDTO dto) {

    Student student = new Student();

    student.setName(dto.getName());
    student.setEmail(dto.getEmail());
    student.setAge(dto.getAge());
    student.setDepartment(dto.getDepartment());

    return studentRepository.save(student);
}
public Student updateStudentFromDTO(int id, StudentRequestDTO dto) {

    Student student = getStudentById(id);

    student.setName(dto.getName());
    student.setEmail(dto.getEmail());
    student.setAge(dto.getAge());
    student.setDepartment(dto.getDepartment());

    return studentRepository.save(student);
}
public List<Student> searchStudentsByName(String name) {
    return studentRepository.findByName(name);
}
public List<Student> searchStudentsByPartialName(String name) {
    return studentRepository.findByNameContaining(name);
}
public List<Student> sortStudents(String sortBy, String direction) {

    Sort sort = direction.equalsIgnoreCase("desc")
            ? Sort.by(sortBy).descending()
            : Sort.by(sortBy).ascending();

    return studentRepository.findAll(sort);
}
}