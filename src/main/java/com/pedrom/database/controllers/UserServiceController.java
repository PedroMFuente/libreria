package com.pedrom.database.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrom.database.exceptions.RecordNotFoundException;
import com.pedrom.database.model.Review;
import com.pedrom.database.model.User;
import com.pedrom.database.services.UserService;

@RestController
@RequestMapping("/user")
public class UserServiceController {

	@Autowired
	UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> list = service.getAllUsers();
		
		return new ResponseEntity<List<User>>(list,new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws RecordNotFoundException{
		User entity = service.getUserById(id);
		
		return new ResponseEntity<User>(entity,new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User myuser) throws RecordNotFoundException{
		User created = service.createUser(myuser);
		
		return new ResponseEntity<User>(created,new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<User> UpdateBook(@RequestBody User myuser) throws RecordNotFoundException{
		User update = service.UpdateUser(myuser);
		
		return new ResponseEntity<User>(update,new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException{
		service.deleteUserById(id);
		
		return HttpStatus.FORBIDDEN;
	}
}
