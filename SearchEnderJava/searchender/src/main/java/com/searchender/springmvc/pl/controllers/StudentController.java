package com.searchender.springmvc.pl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.searchender.springmvc.bl.services.StudentService;
import com.searchender.springmvc.pl.entities.StudentEntity;

@Controller
@RequestMapping("/StudentController")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(path="/createStudent",method=RequestMethod.POST)
	@ResponseBody
	public StudentEntity createStudent(@RequestBody StudentEntity studentEntity) {
		
		System.out.println(studentEntity);
		
		try {
			studentService.createStudent(studentEntity);
			
		} catch (Exception e) {
			
			System.out.println("Exception=> "+e.getMessage());
			e.printStackTrace();
			return studentEntity;
		}

		return studentEntity;
	}
	
	@RequestMapping(path="/readStudent",method=RequestMethod.GET)
	@ResponseBody
	public String readStudent() {

		return "Student Read";
	}
	
	@RequestMapping(path="/updateStudent",method=RequestMethod.PUT)
	@ResponseBody
	public StudentEntity updateStudent(@RequestBody StudentEntity studentEntity) {
		
		System.out.println(studentEntity);
		
		studentService.updateStudent(studentEntity);
		
		System.out.println(studentEntity);

		return studentEntity;
	}

	@RequestMapping(path="/deleteStudent",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteStudent(@RequestBody StudentEntity studentEntity) {
		
		studentService.deleteStudent(studentEntity);
		return "Student Deleted";
	}
	
	@RequestMapping(path="/deleteAllStudent",method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteAllStudent(@RequestBody List<StudentEntity> studentEntityList){
	
		studentService.deleteAllStudent(studentEntityList);
		return "All Students in the list deleted";
	}

}
