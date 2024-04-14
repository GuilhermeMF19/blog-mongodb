package com.gmf.blogmongodb.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gmf.blogmongodb.domain.Post;
import com.gmf.blogmongodb.repository.PostRepository;
import com.gmf.blogmongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public List<Post> findAll() {
		return repo.findAll();
	}

	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public Post insert(Post obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		try {
			repo.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		}
	}
	
	public Post update(Post obj) {
		Post newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	private void updateData(Post newObj, Post obj) {
		newObj.setDate(obj.getDate());
		newObj.setTitle(obj.getTitle());
		newObj.setBody(obj.getBody());
		newObj.setAuthor(obj.getAuthor());
	}
	
	public List<Post> findByTitle(String text) {
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
