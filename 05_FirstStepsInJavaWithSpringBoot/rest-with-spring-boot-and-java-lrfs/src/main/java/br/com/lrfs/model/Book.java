package br.com.lrfs.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "title", nullable=false, length=80)
	private String title;
	
	@Column(name = "author", nullable=false, length=80)
	private String author;
	
	@Column(nullable=false, length=100)
	private String editor;
	
	@Column(nullable=false, length=100)
	private String genre;
	
	@Column(nullable=false, length=100)
	private String isbn;
	
	public Book() {}

	@Override
	public int hashCode() {
		return Objects.hash(author, editor, genre, id, isbn, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(editor, other.editor)
				&& Objects.equals(genre, other.genre) && id == other.id && Objects.equals(isbn, other.isbn)
				&& Objects.equals(title, other.title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	
	
	
}
