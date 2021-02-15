package com.pedrom.database.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrom.database.exceptions.RecordNotFoundException;
import com.pedrom.database.model.Author;
import com.pedrom.database.repositories.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	AuthorRepository repository;
	
	public List<Author> getAllAuthors(){
		List<Author> authorlist = repository.findAll();
		
		if(authorlist.size()>0) {
			return authorlist;
		}else {
			return new ArrayList<Author>();
		}
	}
	
	public Author getAuthorById(Long id) throws RecordNotFoundException{
		Optional<Author> author = repository.findById(id);
		
		if(author.isPresent()) {
			return author.get();
		}else {
			throw new RecordNotFoundException("Not found for given ID", id);
		}
	}
	
	public Author createAuthor(Author entity) {
		entity = repository.save(entity);
		return entity;
	}
	
	public Author UpdateAuthor(Author entity) throws RecordNotFoundException{
		
		if(entity.getId()!=null) {
			Optional<Author> author= repository.findById(entity.getId());
			
			if(author.isPresent()) {
				Author newAuthor= author.get();
				newAuthor.setName(entity.getName());
				newAuthor.setBooklist(entity.getBooklist());
				
				return newAuthor;
			}else {
				throw new RecordNotFoundException("Author not found", entity.getId());
			}
		}else {
			throw new RecordNotFoundException("No ID of author given", 01);
		}
	}
	
	public void deleteAuthorById(Long id)  throws RecordNotFoundException{
		Optional<Author> author = repository.findById(id);
		
		if(author.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new RecordNotFoundException("No author record exist for given id", id);
		}
	}
}
