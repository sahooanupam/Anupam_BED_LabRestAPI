package com.gl.studenteventmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gl.studenteventmanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("select st from Student st where st.firstName like %:firstName%")
	public List<Student> searchStudent(String firstName);
}
