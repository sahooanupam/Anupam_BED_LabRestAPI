package com.gl.studenteventmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.studenteventmanagement.entity.Student;
import com.gl.studenteventmanagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student findStudentById(int id) {
		Student student=null;
		Optional<Student> result= studentRepository.findById(id);
		if(result.isPresent()) {
			student=result.get();
		}
		else {
			throw new RuntimeException("Student with student id "+id+" is not found on databse");
		}
		
		return student;
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
		
	}

	@Override
	public void deleteStudent(int id) {
		
		studentRepository.deleteById(id);
	}

	@Override
	public List<Student> searchStudent(String studentFirstName) {
		return studentRepository.searchStudent(studentFirstName);
	
	}
	
	

}
