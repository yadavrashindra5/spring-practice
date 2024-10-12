package com.practice.services;

import com.practice.entities.Student;
import com.practice.response.PageableResponse;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudent(int student_id);
    void deleteStudent(int student_id);
    Student updateStudent(int student_id,Student student);
    PageableResponse<Student> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);
}
