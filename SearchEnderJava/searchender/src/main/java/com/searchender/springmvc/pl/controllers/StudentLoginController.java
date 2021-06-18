package com.searchender.springmvc.pl.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;

import com.searchender.springmvc.bl.services.StudentLoginService;
import com.searchender.springmvc.pl.entities.StudentEntity;
import com.searchender.springmvc.pl.entities.StudentLoginEntity;

@Controller
@RequestMapping("/StudentLoginController")
public class StudentLoginController {
	
	@Autowired
	private StudentLoginService studentLoginService;
	
	@RequestMapping(path="/getStudentByUserNameAndUserPassword", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<StudentEntity> getStudentByUserNameAndUserPassword(@RequestBody StudentLoginEntity studentLoginEntity){
		
		System.out.println(studentLoginEntity);
		
		List<StudentEntity> listOfStudentEntity=null;
		
		try {
			listOfStudentEntity=studentLoginService.findByUserNameAndUserPassword(studentLoginEntity.getUserName(), studentLoginEntity.getUserPassword());
			
			System.out.println("listOfStudentEntity.size()=> "+listOfStudentEntity.size());
			
			if(listOfStudentEntity==null || listOfStudentEntity.size()==0){
				
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
				
			}
			else if(listOfStudentEntity.size()==1){
				
				return ResponseEntity.of(Optional.of(listOfStudentEntity.get(0)));
			}
			else{
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}

}
