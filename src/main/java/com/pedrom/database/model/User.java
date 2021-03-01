package com.pedrom.database.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="mail")
	private String mail;
	
	@Column(name="nick")
	private String nick;
	
	@Column(name="password")
	private String password;
	
	@Column(name="image")
	private String image;
	
	//lista de libros a los que le has dado like
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "likeUser")
	private List<Book> bookslike;
	
	//lista de reviews que has hecho
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, mappedBy = "creator")
	private List<Review> reviewlist;

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public List<Book> getBookslike() {
		return bookslike;
	}


	public void setBookslike(List<Book> bookslike) {
		this.bookslike = bookslike;
	}


	public List<Review> getReviewlist() {
		return reviewlist;
	}


	public void setReviewlist(List<Review> reviewlist) {
		this.reviewlist = reviewlist;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", mail=" + mail + ", nick=" + nick + ", password=" + password + ", image=" + image
				+ ", bookslike=" + bookslike + ", reviewlist=" + reviewlist + "]";
	}
}
