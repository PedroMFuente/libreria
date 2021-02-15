package com.pedrom.database.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrom.database.exceptions.RecordNotFoundException;
import com.pedrom.database.model.Review;
import com.pedrom.database.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository repository;
	
	public List<Review> getAllReviews(){
		List<Review> reviewlist = repository.findAll();
		
		if(reviewlist.size()>0) {
			return reviewlist;
		}else {
			return new ArrayList<Review>();
		}
	}
	
	public Review getReviewById(Long id) throws RecordNotFoundException{
		Optional<Review> review = repository.findById(id);
		
		if(review.isPresent()) {
			return review.get();
		}else {
			throw new RecordNotFoundException("Not found for given id", id);
		}
	}
	
	public Review createReview(Review entity) {
		entity = repository.save(entity);
		return entity;
	}
	
	public Review UpdateReview(Review entity) throws RecordNotFoundException{
		
		if(entity.getId()!=null) {
			Optional<Review> review= repository.findById(entity.getId());
			
			if(review.isPresent()) {
				Review newreview= review.get();
				newreview.setBook(entity.getBook());
				newreview.setImage(entity.getImage());
				newreview.setText(entity.getText());
				newreview.setUser(entity.getUser());
				
				return newreview;
			}else {
				throw new RecordNotFoundException("Review not found", entity.getId());
			}
		}else {
			throw new RecordNotFoundException("No Id of review given", 01);
		}
	}
	
	public void deleteReviewById(Long id) throws RecordNotFoundException{
		Optional<Review> review = repository.findById(id);
		
		if(review.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new RecordNotFoundException("No review record exist for given id", id);
		}
	}
}
