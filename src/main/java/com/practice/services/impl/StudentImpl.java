package com.practice.services.impl;

import com.practice.Helper.Helper;
import com.practice.entities.Student;
import com.practice.exceptions.ResourceNotFoundException;
import com.practice.repositories.StudentRepository;
import com.practice.response.PageableResponse;
import com.practice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    @Override
    public Student getStudent(int student_id) {
        Student student = studentRepository.findById(student_id).orElseThrow(() -> new ResourceNotFoundException("Provided id is not present"));
        return student;
    }

    @Override
    public void deleteStudent(int student_id) {
        studentRepository.deleteById(student_id);
    }

    @Override
    public Student updateStudent(int student_id, Student student) {
        Student student1 = studentRepository.findById(student_id).orElseThrow(() -> new ResourceNotFoundException("Provided id is not present"));
        student1.setName(student.getName());

        Student savedStudent = studentRepository.save(student1);
        return savedStudent;
    }

    @Override
    public PageableResponse<Student> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc"))?(Sort.by(sortBy).descending()): (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Student> page = studentRepository.findAll(pageable);
        PageableResponse<Student> pageableResponse = Helper.getPageableResponse(page, Student.class);
        return pageableResponse;
    }
}
