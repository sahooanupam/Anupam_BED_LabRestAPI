package com.gl.studenteventmanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gl.studenteventmanagement.entity.Student;
import com.gl.studenteventmanagement.repository.StudentRepository;
import com.gl.studenteventmanagement.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping("/main")
	public String main() {
		return "main";
	}

	@RequestMapping("/list")
	public String findAllStudents(Model model) {
		List<Student> students = studentService.findAllStudents();
		model.addAttribute("students", students);
		return "list";
	}

	@RequestMapping("/showStudentFormForAdd")
	public String showStudentFormForAdd(ModelMap modelMap, @ModelAttribute("student") Student stud,
			@ModelAttribute("String") String rError) {
		modelMap.addAttribute("student", stud);
		if (rError != null)
			modelMap.addAttribute("Err", rError);
		return "student-form";
	}

	@RequestMapping("/showStudentFormForUpdate")
	public String showStudentFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student student = studentService.findStudentById(id);
		model.addAttribute("student", student);
		return "student-form";
	}

	@RequestMapping("/save")
	public String saveStudent(@ModelAttribute("student") Student thestud, ModelMap modelMap, RedirectAttributes ra) {

		String err = "Record already exists";
		String errL = "Fields cannot be blank";

		if (thestud.getFirstName().isEmpty() || thestud.getLastName().isEmpty() || thestud.getCourse().isEmpty()
				|| thestud.getCountry().isEmpty()) {
			ra.addFlashAttribute("student", thestud);
			ra.addFlashAttribute("String", errL);
			return "redirect:/students/showStudentFormForAdd";
		}

		List<Student> listOfStudents = studentService.findAllStudents();
		for (Student stud : listOfStudents) {
			if (stud.getFirstName().equalsIgnoreCase(thestud.getFirstName())
					&& stud.getLastName().equalsIgnoreCase(thestud.getLastName())
					&& stud.getCourse().equalsIgnoreCase(thestud.getCourse())
					&& stud.getCountry().equalsIgnoreCase(thestud.getCountry())) {
				System.out.println("STUD" + thestud.toString());
				ra.addFlashAttribute("student", thestud);
				ra.addFlashAttribute("String", err);
				return "redirect:/students/showStudentFormForAdd";

			}
			
		}
	    Student studentTemp=null;   
		if(thestud.getStudentId()!=0) {
			studentTemp=studentService.findStudentById(thestud.getStudentId());
			studentTemp.setFirstName(thestud.getFirstName());
			studentTemp.setLastName(thestud.getLastName());
			studentTemp.setCourse(thestud.getCourse());
			studentTemp.setCountry(thestud.getCountry());
			
		}
		else
			studentTemp=new Student(thestud);
		studentService.saveStudent(studentTemp);
		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deleteStudent(id);
		return "redirect:/students/list";
	}

	@RequestMapping("/search")
	public String searchStudent(@RequestParam("name") String studentName, Model theModel) {
		List<Student> students = studentService.searchStudent(studentName);
		if (studentName.trim().isEmpty()) {
			return "redirect:/students/list";
		} else {
			// else, search by first name and last name
			List<Student> theStudents = studentService.searchStudent(studentName);

			// add to the spring model
			theModel.addAttribute("students", theStudents);

			// send to list-Books
			return "list-students";
		}

	}

	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}
}
