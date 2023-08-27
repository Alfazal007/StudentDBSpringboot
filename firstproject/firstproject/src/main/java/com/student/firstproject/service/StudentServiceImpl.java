package com.student.firstproject.service;

import com.student.firstproject.entity.Student;
import com.student.firstproject.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepo studentRepo;
    @Override
    public Student addNewStudent(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentThroughId(Long id) {
        return studentRepo.findById(id).get();
    }

    @Override
    public Student getByName(String name) {
        return studentRepo.findByName(name);
    }

    @Override
    public void deleteThroughId(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public Student updateStudentInfo(Student student, Long id) {
        Student studentToUpdate = studentRepo.findById(id).get();
        if(Objects.nonNull(student.getName()) && !("".equalsIgnoreCase(student.getName()))) {
            studentToUpdate.setName(student.getName());
        }
        if(Objects.nonNull(student.getPhone()) && !("".equalsIgnoreCase(student.getPhone()))) {
            studentToUpdate.setPhone(student.getPhone());
        }
        return studentRepo.save(studentToUpdate);
    }
}
