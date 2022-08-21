package com.example.service;

import com.example.entity.Student;

import java.util.List;

public interface StudentService {
    void add(Student student);

    List<Student> getStudentList();
}
