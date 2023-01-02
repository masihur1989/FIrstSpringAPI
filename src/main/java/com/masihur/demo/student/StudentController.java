package com.masihur.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller
 *
 * @author Masihur R. Maruf
 * @created 1/1/23 8:00 PM
 */
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return this.studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Optional<Student> getStudent(@PathVariable("studentId") Long id) {
        return this.studentService.getStudent(id);
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        this.studentService.createStudent(student);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        this.studentService.updateStudent(studentId, name, email);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        this.studentService.deleteStudent(id);
    }
}
