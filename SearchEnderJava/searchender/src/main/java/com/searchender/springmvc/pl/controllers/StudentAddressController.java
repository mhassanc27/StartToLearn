package com.searchender.springmvc.pl.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.searchender.springmvc.bl.services.StudentAddressService;
import com.searchender.springmvc.pl.entities.StudentAddressEntity;

@Controller
@RequestMapping("/StudentAddressController")
public class StudentAddressController {
	
	@Autowired
	private StudentAddressService studentAddressService;

	@RequestMapping(path="/createStudentAddress")
	@ResponseBody
	public List <StudentAddressEntity> createStudentAddress(@RequestBody StudentAddressEntity studentAddressEntity){
		
		List <StudentAddressEntity> listOfStudentAddressEntity=null;
		
		try {
			studentAddressService.createStudentAddress(studentAddressEntity);
			listOfStudentAddressEntity=new ArrayList<StudentAddressEntity>();
			listOfStudentAddressEntity.add(studentAddressEntity);
			System.out.println("listOfStudentAddressEntity=> "+listOfStudentAddressEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
		
		return listOfStudentAddressEntity;
	}
	
	@RequestMapping(path="/readStudentAddressByStudentId/{studentId}",method=RequestMethod.GET)
	@ResponseBody
	public List <StudentAddressEntity> readStudentAddressByStudentId(@PathVariable int studentId){
		
		List <StudentAddressEntity> listOfStudentAddressEntity=null;
		
		try {
			listOfStudentAddressEntity=studentAddressService.readStudentAddressByStudentId(studentId);
			System.out.println("listOfStudentAddressEntity=> "+listOfStudentAddressEntity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfStudentAddressEntity;
	}
	
	@RequestMapping(path="/readStudentAddressById/{id}")
	public List <StudentAddressEntity> readStudentAddressById(@PathVariable int id){
		
		List <StudentAddressEntity> listOfStudentAddressEntity=null;
		
		return listOfStudentAddressEntity;
	}
	@RequestMapping(path="updateStudentAddress",method=RequestMethod.PUT)
	@ResponseBody
	public List <StudentAddressEntity> updateStudentAddress(@RequestBody StudentAddressEntity studentAddressEntity){
		
		List <StudentAddressEntity> listOfStudentAddressEntity=null;
		
		try {
			studentAddressService.updateStudentAddress(studentAddressEntity);
			listOfStudentAddressEntity=new ArrayList<StudentAddressEntity>();
			listOfStudentAddressEntity.add(studentAddressEntity);
			System.out.println("Updated listOfStudentAddressEntity=> "+listOfStudentAddressEntity);
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
						
		return listOfStudentAddressEntity;
		
	}
	
	@RequestMapping(path="deleteStudentAddress",method=RequestMethod.DELETE)
	@ResponseBody
	public List <StudentAddressEntity> deleteStudentAddress(@RequestBody StudentAddressEntity studentAddressEntity){
		List <StudentAddressEntity> listOfStudentAddressEntity=null;
		
		try{
			
			int studentId=studentAddressEntity.getStudentId();
			studentAddressService.deleteStudentAddress(studentAddressEntity);
			listOfStudentAddressEntity=studentAddressService.readStudentAddressByStudentId(studentId);
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		return listOfStudentAddressEntity;
	}
	
}
