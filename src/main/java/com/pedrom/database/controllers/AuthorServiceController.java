package com.pedrom.database.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pedrom.database.exceptions.RecordNotFoundException;
import com.pedrom.database.model.Author;
import com.pedrom.database.services.AuthorService;

@RestController
@RequestMapping("/author")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthorServiceController {

	@Autowired
	AuthorService service;
	
	@GetMapping
	public ResponseEntity<List<Author>> getAllAuthors(){
		List<Author> list = service.getAllAuthors();
		
		return new ResponseEntity<List<Author>>(list,new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) throws RecordNotFoundException{
		Author entity = service.getAuthorById(id);
		
		return new ResponseEntity<Author>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Author> createAuthor(@RequestBody Author myauthor) throws RecordNotFoundException{
		Author created = service.createAuthor(myauthor);
		
		return new ResponseEntity<Author>(created, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Author> UpdateAuthor(@RequestBody Author myauthor) throws RecordNotFoundException{
		Author update= service.UpdateAuthor(myauthor);
		
		return new ResponseEntity<Author>(update, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException{
		service.deleteAuthorById(id);
		
		return HttpStatus.FORBIDDEN;
	}
}
