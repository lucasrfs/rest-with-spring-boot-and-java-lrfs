package br.com.lrfs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrfs.data.vo.v1.BookVO;
import br.com.lrfs.services.BookServices;
import br.com.lrfs.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/book/v1")
@Tag(name="Book", description = "Endpoint para busca de livros")
public class BookController {
	
	@Autowired
	private BookServices service;
	
	@GetMapping(value = "/{id}",
				produces= { MediaType.APPLICATION_JSON,
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML} )
	@Operation(summary = "Busca um livro", description = "Busca um registro no banco de dados por id",
	tags= {"Book"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = BookVO.class))
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	})
	public BookVO findByid(
			@PathVariable(value="id") Long id
			) throws Exception{
		
		return service.findById(id);				 
	}
	
	@GetMapping(produces= { MediaType.APPLICATION_JSON,
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML } )
	@Operation(summary = "Busca todas as pessoas", description = "Busca todos os registros do banco",
		tags= {"Book"},
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = {
								@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
								)
								}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
		})
	public List<BookVO> findAll() throws Exception {
		return service.findAll();
	}
	
	@PostMapping(produces={ MediaType.APPLICATION_JSON,
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML } ,
				consumes={  MediaType.APPLICATION_JSON,
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML } )
	@Operation(summary = "Grava um livro", description = "Grava um registro no banco de dados por id",
	tags= {"Book"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = BookVO.class))
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	})
	public BookVO create(@RequestBody BookVO book) throws Exception  {
		
		return service.create(book);
	}
	
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON,
								MediaType.APPLICATION_XML,
								MediaType.APPLICATION_YML } ,
				produces = { MediaType.APPLICATION_JSON,
								MediaType.APPLICATION_XML,
								MediaType.APPLICATION_YML } )
	@Operation(summary = "Atualiza uma pessoa", description = "Atualiza um registro no banco de dados por id",
	tags= {"Book"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = BookVO.class))
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	})
	public BookVO update(@RequestBody BookVO book) throws Exception {
		return service.update(book);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Apaga um livro", description = "Apaga um registro no banco de dados por id",
	tags= {"Book"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = BookVO.class))
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
}
