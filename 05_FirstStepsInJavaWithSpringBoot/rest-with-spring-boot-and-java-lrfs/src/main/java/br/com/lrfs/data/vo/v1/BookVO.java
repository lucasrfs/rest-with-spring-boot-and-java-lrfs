package br.com.lrfs.data.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@JsonPropertyOrder({"id","address","first_name","last_name","gender"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	@Mapping("id")
	private long key;
	@JsonProperty("title")
	private String title;
	@JsonProperty("author")
	private String author;
	@JsonProperty("editor")
	private String editor;
	@JsonProperty("genre")
	private String genre;
	@JsonProperty("isbn")
	private String isbn;
	
	public BookVO() {}
	
	

	public long getKey() {
		return key;
	}



	public void setKey(long key) {
		this.key = key;
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(author, editor, genre, isbn, key, title);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookVO other = (BookVO) obj;
		return Objects.equals(author, other.author) && Objects.equals(editor, other.editor)
				&& Objects.equals(genre, other.genre) && Objects.equals(isbn, other.isbn) && key == other.key
				&& Objects.equals(title, other.title);
	}

	
	
	
}
