package com.masihur.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service Layer
 *
 * @author Masihur R. Maruf
 * @created 1/1/23 8:10 PM
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudent(Long studentId) {
        if (!studentRepository.existsById(studentId))
            throw new IllegalStateException("student with id " + studentId + " doesn't exist");
        return studentRepository.findById(studentId);
    }

    public void createStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id " + studentId + " doesn't exist"));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) throw new IllegalStateException("email taken");
            student.setEmail(email);
        }
    }

    public void deleteStudent(Long studentId) {
        if (!studentRepository.existsById(studentId))
            throw new IllegalStateException("student with id " + studentId + " doesn't exist");
        studentRepository.deleteById(studentId);
    }
}
