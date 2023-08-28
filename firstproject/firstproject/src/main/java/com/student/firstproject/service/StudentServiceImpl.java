package com.student.firstproject.service;

import com.student.firstproject.dto.StudentRequest;
import com.student.firstproject.entity.Student;
import com.student.firstproject.exception.StudentListEmptyException;
import com.student.firstproject.exception.StudentNotFoundException;
import com.student.firstproject.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepo studentRepo;
    @Override
    public Student addNewStudent(StudentRequest student) {
        Student complete_student = Student.builder()
                .name(student.getName())
                .phone(student.getPhone())
                .build();
        return studentRepo.save(complete_student);
    }

    @Override
    public List<Student> getAllStudents() throws StudentListEmptyException {
        List<Student> students =  studentRepo.findAll();
        if(students.isEmpty()) {
            throw new StudentListEmptyException("There are no students");
        } else {
            return students;
        }
    }

    @Override
    public Student getStudentThroughId(Long id) throws StudentNotFoundException {
        Optional<Student> studentToBeReturned =  studentRepo.findById(id);
        if(studentToBeReturned.isPresent()) {
            return  studentToBeReturned.get();
        } else {
            throw new StudentNotFoundException("The student with id " + id + " was not found");
        }
    }

    @Override
    public Student getByName(String name) throws StudentNotFoundException {
        Student studentToBeReturned =  studentRepo.findByName(name);
        if(studentToBeReturned == null) {
            throw new StudentNotFoundException("The student with this name was not found");
        } else {
            return studentToBeReturned;
        }
    }

    @Override
    public String deleteThroughId(Long id) throws StudentNotFoundException {
        Optional<Student> studentToBeDeleted = studentRepo.findById(id);
        if(studentToBeDeleted.isPresent()) {
            studentRepo.deleteById(id);
            return "The student with id = " + id + " has been deleted";
        }
        else {
            throw new StudentNotFoundException("The student with id " + id + " was not found");
        }
    }

    @Override
    public Student updateStudentInfo(@Valid Student student, Long id) throws StudentNotFoundException {
        Optional<Student> studentToUpdateFind = studentRepo.findById(id);

        if(studentToUpdateFind.isPresent()) {
            Student studentToUpdate = studentToUpdateFind.get();
        if(Objects.nonNull(student.getName()) && !("".equalsIgnoreCase(student.getName()))) {
            studentToUpdate.setName(student.getName());
        }
        if(Objects.nonNull(student.getPhone()) && !("".equalsIgnoreCase(student.getPhone()))) {
            studentToUpdate.setPhone(student.getPhone());
        }
        return studentRepo.save(studentToUpdate);
        }
        else {
            throw new StudentNotFoundException("The student with id = " + id + " was not found in the database.");
        }

    }
}
