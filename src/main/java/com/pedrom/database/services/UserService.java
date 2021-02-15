package com.pedrom.database.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedrom.database.exceptions.RecordNotFoundException;
import com.pedrom.database.model.User;
import com.pedrom.database.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public List<User> getAllUsers(){
		List<User> userlist= repository.findAll();
		
		if(userlist.size()>0) {
			return userlist;
		}else {
			return new ArrayList<User>();
		}
	}
	
	public User getUserById(Long id) throws RecordNotFoundException{
		Optional<User> user= repository.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new RecordNotFoundException("Not found for given id", id);
		}
	}
	
	public User createUser(User entity) {
		entity = repository.save(entity);
		return entity;
	}
	
	public User UpdateUser(User entity) throws RecordNotFoundException{
		
		if(entity.getId()!=null) {
			Optional<User> user = repository.findById(entity.getId());
			
			if(user.isPresent()) {
				User newuser = user.get();
				newuser.setNick(entity.getNick());
				newuser.setMail(entity.getMail());
				newuser.setPassword(entity.getPassword());
				newuser.setImage(entity.getImage());
				newuser.setBookslike(entity.getBookslike());
				newuser.setReviewlist(entity.getReviewlist());
				
				return newuser;
			}else {
				throw new RecordNotFoundException("User not found", entity.getId());
			}
		}else {
			throw new RecordNotFoundException("No id of user given", 01);
		}
	}
	
	public void deleteUserById(Long id) throws RecordNotFoundException{
		Optional<User> user = repository.findById(id);
		
		if(user.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new RecordNotFoundException("No user record exist for given id", id);
		}
	}
}
