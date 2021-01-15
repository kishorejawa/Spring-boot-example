package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.SurekhaDataApplication;
import com.example.demo.Exception.ResourceNotFoundException;
import com.example.demo.model.modelEntity;
import com.example.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/emp")
public class dataController {
	
	private static final Logger logger = LoggerFactory.getLogger(SurekhaDataApplication.class);
	
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@RequestMapping(value = "/data")
	public @ResponseBody modelEntity getalldata()
	{
		logger.info("List of data sent over url");
		modelEntity datas = new modelEntity();
		datas.setId(1);
		datas.setName("Surekha");
		datas.setAge(27);
		datas.setHobby("Cooking");
		return datas;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<modelEntity> getdata()
	//public @ResponseBody modelEntity alldata()
	{
		logger.info("List of data sent over url");
		modelEntity datas = new modelEntity();
		ArrayList<modelEntity> list =  new ArrayList<modelEntity>();
		
		datas.setId(1);
		datas.setName("Surekha");
		datas.setAge(27);
		datas.setHobby("Cooking");
		//return datas;
		list.add(datas);
		list.add(new modelEntity(2,"Kishore","Cricket",30));
		
		return list;
	}
	
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<modelEntity> getId(@PathVariable(value = "id") long empId) throws ResourceNotFoundException
	
	{
		logger.info("Get employee details");
		modelEntity data = empRepo.findById(empId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found" +empId) );
				
		return ResponseEntity.ok().body(data); 
	}
	
	//@RequestMapping(value = "/test", method = RequestMethod.POST)
	@PostMapping("/p")
	public void sendRequest(@RequestBody modelEntity mData) {
		
		logger.info("Insert Data into DB");		
		 empRepo.save(mData);
		
	}
	@RequestMapping(value = "/da", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PostMapping("/da")
	public modelEntity create( @RequestBody modelEntity data) {
		 return empRepo.save(data);
	}
	
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.PUT)
	public ResponseEntity<modelEntity> dataById(@PathVariable(value = "id") long empId, @RequestBody modelEntity updatedData) throws ResourceNotFoundException
	{
		logger.info("Updating the employee in db");
		modelEntity data = empRepo.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found" + empId));
		data.setName(updatedData.getName());
		data.setHobby(updatedData.getHobby());
		data.setAge(updatedData.getAge());
		empRepo.save(data);
		return ResponseEntity.ok().body(data);			
	}
	
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.DELETE)
	public void deleteID(@PathVariable(value = "id")long empId) throws ResourceNotFoundException
	{
		logger.info("Deleting the record");
		modelEntity data = empRepo.findById(empId)
				.orElseThrow(()-> new ResourceNotFoundException("Employee not found" + empId));
		empRepo.delete(data);
		
	}
	

}
