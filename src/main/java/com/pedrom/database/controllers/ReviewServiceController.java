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
import com.pedrom.database.model.Review;
import com.pedrom.database.services.ReviewService;

@RestController
@RequestMapping("/review")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReviewServiceController {
	
	@Autowired
	ReviewService service;
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews(){
		List<Review> list = service.getAllReviews();
		
		return new ResponseEntity<List<Review>>(list,new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Review> getReviewById(@PathVariable("id") Long id) throws RecordNotFoundException{
		Review entity = service.getReviewById(id);
		
		return new ResponseEntity<Review>(entity,new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Review> createReview(@RequestBody Review myreview) throws RecordNotFoundException{
		Review created = service.createReview(myreview);
		
		return new ResponseEntity<Review>(created,new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Review> UpdateBook(@RequestBody Review myreview) throws RecordNotFoundException{
		Review update = service.UpdateReview(myreview);
		
		return new ResponseEntity<Review>(update,new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException{
		service.deleteReviewById(id);
		
		return HttpStatus.FORBIDDEN;
	}
}
