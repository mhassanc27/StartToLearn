package com.searchender.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestClass {
	private int id;
	
	public static void main(String[] args) {
		
		Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());  
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        
        Timestamp tt= Timestamp.valueOf(formatter.format(ts));
        
        System.out.println(formatter.format(ts));
	}
	
}
