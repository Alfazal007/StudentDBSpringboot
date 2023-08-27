package com.student.firstproject.service;


import com.student.firstproject.entity.Student;

import java.util.List;

public interface StudentService {
    Student addNewStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentThroughId(Long id);

    Student getByName(String name);

    void deleteThroughId(Long id);

    Student updateStudentInfo(Student student, Long id);
}
