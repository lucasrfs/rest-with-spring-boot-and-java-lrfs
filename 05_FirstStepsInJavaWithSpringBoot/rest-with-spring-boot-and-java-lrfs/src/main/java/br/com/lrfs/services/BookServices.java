package br.com.lrfs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.lrfs.controllers.BookController;
import br.com.lrfs.controllers.PersonController;
import br.com.lrfs.data.vo.v1.BookVO;
import br.com.lrfs.exceptions.RequiredObjectIsNullException;
import br.com.lrfs.exceptions.ResourceNotFoundException;
import br.com.lrfs.mapper.DozerMapper;
import br.com.lrfs.model.Book;
import br.com.lrfs.repositories.BookRepository;

@Service
public class BookServices {
	
	
	private Logger logger  = Logger.getLogger(BookServices.class.getName());

	@Autowired
	BookRepository repository;
	
	public List<BookVO> findAll(){
		
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books
			.stream()
			.forEach(p -> {
				try {
					p.add(linkTo(methodOn(PersonController.class).findByid(p.getKey())).withSelfRel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		return books;
	}
	
	public BookVO findById(Long id) throws Exception{		
		
		logger.info("Buscando uma pessoa");		
		var entity = repository.findById(id).
					orElseThrow(() -> new ResourceNotFoundException("No records found"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findByid(id)).withSelfRel());
		return vo;
	}
	
	public BookVO create(BookVO book) throws Exception
	{
		if(book == null) throw new RequiredObjectIsNullException();
		var entity = DozerMapper.parseObject(book, Book.class);
		var ret = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		ret.add(linkTo(methodOn(BookController.class).findByid(ret.getKey())).withSelfRel());
		return ret;
	}
	
	public BookVO update(BookVO book) throws Exception
	{
		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("BookVO not found"));
		entity.setAuthor(book.getAuthor());
		entity.setTitle(book.getTitle());
		entity.setEditor(book.getEditor());
		entity.setGenre(book.getGenre());
		entity.setIsbn(book.getIsbn());
		
		var ret = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		ret.add(linkTo(methodOn(BookController.class).findByid(ret.getKey())).withSelfRel());
		return ret;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
