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
import com.pedrom.database.model.Genre;
import com.pedrom.database.services.GenreService;

@RestController
@RequestMapping("/genre")
@CrossOrigin(origins = "*", maxAge = 3600)
public class GenreServiceController {

	@Autowired
	GenreService service;
	
	@GetMapping
	public ResponseEntity<List<Genre>> getAllGenres(){
		List<Genre> list = service.getAllGenres();
		
		return new ResponseEntity<List<Genre>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) throws RecordNotFoundException{
		Genre entity = service.getGenreById(id);
		
		return new ResponseEntity<Genre>(entity, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Genre> createGenre(@RequestBody Genre mygenre) throws RecordNotFoundException{
		Genre created = service.createGenre(mygenre);
		
		return new ResponseEntity<Genre>(created, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Genre> UpdateBook(@RequestBody Genre mygenre) throws RecordNotFoundException{
		Genre update = service.UpdateGenre(mygenre);
		
		return new ResponseEntity<Genre>(update, new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException{
		service.deleteGenreById(id);
		
		return HttpStatus.FORBIDDEN;
	}
}
