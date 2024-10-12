package com.practice.controllers;

import com.practice.entities.Student;
import com.practice.response.ApiResponse;
import com.practice.response.PageableResponse;
import com.practice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveStudent(@RequestBody Student student) {
        Student student1 = studentService.saveStudent(student);
        ApiResponse studentIsCreated = ApiResponse.builder().message("student is created").status(HttpStatus.OK).build();
        return new ResponseEntity<>(studentIsCreated, HttpStatus.CREATED);
    }

    @GetMapping("/{student_id}")
    public ResponseEntity<Student> getStudent(@PathVariable(name = "student_id") int student_id) {
        Student student = studentService.getStudent(student_id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{student_id}")
    public void deleteStudent(@PathVariable int student_id) {
        studentService.deleteStudent(student_id);
    }

    @PutMapping("/{student_id}")
    public ResponseEntity<Student> updateStudent(@PathVariable(name = "student_id") int student_id, @RequestBody Student student) {
        Student student1 = studentService.updateStudent(student_id, student);
        return new ResponseEntity<>(student1, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageableResponse> getAll(
            @RequestParam(value = "pageNumber", defaultValue = "10", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        PageableResponse<Student> pageableResponse = studentService.getAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(pageableResponse, HttpStatus.OK);
    }
}
