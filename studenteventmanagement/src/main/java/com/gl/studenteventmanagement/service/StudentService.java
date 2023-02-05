package com.gl.studenteventmanagement.service;

import java.util.List;

import com.gl.studenteventmanagement.entity.Student;

public interface StudentService {

	List<Student> findAllStudents();
	Student findStudentById(int id);
	Student saveStudent(Student student);
	void deleteStudent(int id);
	List<Student> searchStudent(String studentName);
}
