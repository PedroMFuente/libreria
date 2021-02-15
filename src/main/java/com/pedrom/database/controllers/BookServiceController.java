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
import com.pedrom.database.model.Book;
import com.pedrom.database.services.BookService;

@RestController
@RequestMapping("/book")
public class BookServiceController {

	@Autowired
	BookService service;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> list = service.getAllBooks();
		
		return new ResponseEntity<List<Book>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) throws RecordNotFoundException{
		Book entity = service.getBookById(id);
		
		return new ResponseEntity<Book>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book myBook) throws RecordNotFoundException{
		Book created = service.createBook(myBook);
		
		return new ResponseEntity<Book>(created, new HttpHeaders(), HttpStatus.OK); 
	}
	
	@PutMapping
	public ResponseEntity<Book> UpdateBook(@RequestBody Book myBook) throws RecordNotFoundException{
		Book update = service.UpdateBook(myBook);
		
		return new ResponseEntity<Book>(update, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException{
		service.deleteBookById(id);
		
		return HttpStatus.FORBIDDEN;
	}
}
