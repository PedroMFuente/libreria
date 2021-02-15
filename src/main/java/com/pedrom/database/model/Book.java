package com.pedrom.database.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="Book")
public class Book {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long isbn;
	
	@Column(name="title")
	private String title;
	
	//@Column(name="author")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="Id_Artist")
	private Author author;
	
	@Column(name="synopsis")
	private String synopsis;
	
	//@Column(name="genres")
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="Id_Genre")
	private List<Genre> genres;
	
	@Column(name="image")
	private String image;
	
	@JoinTable(
			name="like",
			joinColumns = @JoinColumn(name="Id_Book",nullable=false),
			inverseJoinColumns = @JoinColumn(name="Id_User",nullable=false)
			)
	@ManyToMany(cascade = CascadeType.ALL)
	private List<User> likeUser;
	
	//reseñas
	@JoinTable(
			name="reviewlist",
			joinColumns = @JoinColumn(name="Id_Book",nullable = false),
			inverseJoinColumns = @JoinColumn(name="Id_Review",nullable=false)
			)
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Review> reviewlist;
	

	public List<User> getLikeUser() {
		return likeUser;
	}

	public void setLikeUser(List<User> likeUser) {
		this.likeUser = likeUser;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public List<Genre> getGenres() {
		return genres;
	}

	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Review> getReviewlist() {
		return reviewlist;
	}

	public void setReviewlist(List<Review> reviewlist) {
		this.reviewlist = reviewlist;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", synopsis=" + synopsis + ", genres="
				+ genres + "]";
	}
}
