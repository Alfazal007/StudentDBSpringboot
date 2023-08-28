package com.student.firstproject.controller;

import com.student.firstproject.dto.StudentRequest;
import com.student.firstproject.entity.Student;
import com.student.firstproject.exception.StudentListEmptyException;
import com.student.firstproject.exception.StudentNotFoundException;
import com.student.firstproject.service.StudentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/students/")
    public ResponseEntity<Student> addNewStudent(@Valid @RequestBody StudentRequest student) {
        return new ResponseEntity<>(studentService.addNewStudent(student), HttpStatus.CREATED);
    }

    @GetMapping("/students/")
    public ResponseEntity<List<Student>> getAllStudents() throws StudentListEmptyException {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.FOUND);
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getById(@PathVariable("id") Long id) throws StudentNotFoundException {
        return new ResponseEntity<>(studentService.getStudentThroughId(id), HttpStatus.FOUND);
    }

    @GetMapping("/students/name/{name}")
    public ResponseEntity<Student> getByName(@PathVariable("name") String name) throws StudentNotFoundException {
        return new ResponseEntity<>(studentService.getByName(name), HttpStatus.FOUND);
    }

    @DeleteMapping("/students/{id}")
    public String deleteById(@PathVariable("id") Long id) throws StudentNotFoundException {
        return studentService.deleteThroughId(id);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudentInfo(@PathVariable("id") Long id,@RequestBody Student student) throws StudentNotFoundException {
        return new ResponseEntity<>(studentService.updateStudentInfo(student, id), HttpStatus.CREATED);
    }
}
