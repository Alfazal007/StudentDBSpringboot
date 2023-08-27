package com.student.firstproject.controller;

import com.student.firstproject.entity.Student;
import com.student.firstproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/students/")
    public Student addNewStudent(@Valid @RequestBody Student student) {
        return studentService.addNewStudent(student);
    }


    @GetMapping("/students/")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    @GetMapping("/students/{id}")
    public Student getById(@PathVariable("id") Long id) {
        return studentService.getStudentThroughId(id);
    }

    @GetMapping("/students/name/{name}")
    public Student getByName(@PathVariable("name") String name) {
        return studentService.getByName(name);
    }

    @DeleteMapping("/students/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        try {
        studentService.deleteThroughId(id);
        return "Deletion successful";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @PutMapping("/students/{id}")
    public Student updateStudentInfo(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.updateStudentInfo(student, id);
    }
}
