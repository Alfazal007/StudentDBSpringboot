package com.student.firstproject.service;


import com.student.firstproject.dto.StudentRequest;
import com.student.firstproject.entity.Student;
import com.student.firstproject.exception.StudentListEmptyException;
import com.student.firstproject.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    Student addNewStudent(StudentRequest student);

    List<Student> getAllStudents() throws StudentListEmptyException;

    Student getStudentThroughId(Long id) throws StudentNotFoundException;

    Student getByName(String name) throws StudentNotFoundException;

    String deleteThroughId(Long id) throws StudentNotFoundException;

    Student updateStudentInfo(Student student, Long id) throws StudentNotFoundException;
}
