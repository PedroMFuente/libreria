package com.pedrom.database.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrom.database.exceptions.RecordNotFoundException;
import com.pedrom.database.model.Book;
import com.pedrom.database.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository repository;
	
	public List<Book> getAllBooks(){
		List<Book> booklist = repository.findAll();
		
		if(booklist.size()>0) {
			return booklist;
		}else{
			return new ArrayList<Book>();
		}
	}
	
	public Book getBookById(Long id) throws RecordNotFoundException{
		Optional<Book> book= repository.findById(id);
		
		if(book.isPresent()) {
			return book.get();
		}else {
			throw new RecordNotFoundException("Not found for given ISBN", id);
		}
	}
	
	public Book createBook(Book entity) {
		entity= repository.save(entity);
		return entity;
	}
	
	public Book UpdateBook(Book entity) throws RecordNotFoundException{
		
		if(entity.getIsbn()!=null) {
			Optional<Book> book = repository.findById(entity.getIsbn());
			
			if(book.isPresent()) {
				Book newbook = book.get();
				newbook.setAuthor(entity.getAuthor());
				newbook.setGenres(entity.getGenres());
				newbook.setSynopsis(entity.getSynopsis());
				newbook.setTitle(entity.getTitle());
				
				return newbook;
			}else {
				throw new RecordNotFoundException("Book not found", entity.getIsbn());
			}
		}else {
			throw new RecordNotFoundException("No ISBN of book given", 0l);
		}
	}
	
	public void deleteBookById(Long id) throws RecordNotFoundException{
		Optional<Book> book = repository.findById(id);
		
		if(book.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new RecordNotFoundException("No book record exist for given id",id);
		}
	}
}
