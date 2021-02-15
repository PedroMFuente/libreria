package com.pedrom.database.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrom.database.exceptions.RecordNotFoundException;
import com.pedrom.database.model.Genre;
import com.pedrom.database.repositories.GenreRepository;

@Service
public class GenreService {

	@Autowired
	GenreRepository repository;
	
	public List<Genre> getAllGenres(){
		List<Genre> genrelist=repository.findAll();
		
		if(genrelist.size()>0) {
			return genrelist;
		}else {
			return new ArrayList<Genre>();
		}
	}
	
	public Genre getGenreById(Long id) throws RecordNotFoundException{
		Optional<Genre> genre= repository.findById(id);
		
		if(genre.isPresent()) {
			return genre.get();
		}else {
			throw new RecordNotFoundException("Not found for given id", id);
		}
	}
	
	public Genre createGenre(Genre entity) {
		entity= repository.save(entity);
		return entity;
	}
	
	public Genre UpdateGenre(Genre entity) throws RecordNotFoundException{
		
		if(entity.getId()!=null) {
			Optional<Genre> genre= repository.findById(entity.getId());
			
			if(genre.isPresent()) {
				Genre newgenre = genre.get();
				newgenre.setName(entity.getName());
				
				return newgenre;
			}else {
				throw new RecordNotFoundException("Genre not found", entity.getId());
			}
		}else {
			throw new RecordNotFoundException("No ID of genre given", 01);
		}
	}
	
	public void deleteGenreById(Long id) throws RecordNotFoundException{
		Optional<Genre> genre = repository.findById(id);
		
		if(genre.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new RecordNotFoundException("No genre record exist for given id", id);
		}
	}
}
