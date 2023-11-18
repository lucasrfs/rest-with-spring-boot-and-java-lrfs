package br.com.lrfs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrfs.data.vo.v1.PersonVO;
import br.com.lrfs.data.vo.v2.PersonVOV2;
import br.com.lrfs.services.PersonServices;
import br.com.lrfs.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name="People", description = "Endpoint para busca de pessoas")
public class PersonController {
	
	@Autowired
	private PersonServices service;
	

	@CrossOrigin(origins = {"http://localhost:8080","http://uol.com.br"})
	@GetMapping(value = "/{id}",
				produces= { MediaType.APPLICATION_JSON,
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML} )
	@Operation(summary = "Busca uma pessoa", description = "Busca um registro no banco de dados por id",
	tags= {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	})
	public PersonVO findByid(
			@PathVariable(value="id") Long id
			) throws Exception{
		
		return service.findById(id);				 
	}
	
	@GetMapping(produces= { MediaType.APPLICATION_JSON,
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML } )
	@Operation(summary = "Busca todas as pessoas", description = "Busca todos os registros do banco",
		tags= {"People"},
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = {
								@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
								)
								}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
		})
	public List<PersonVO> findAll() throws Exception {
		return service.findAll();
	}
	
	@CrossOrigin(origins = {"http://localhost:8080","http://uol.com.br"})
	@PostMapping(produces={ MediaType.APPLICATION_JSON,
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML } ,
				consumes={  MediaType.APPLICATION_JSON,
							MediaType.APPLICATION_XML,
							MediaType.APPLICATION_YML } )
	@Operation(summary = "Grava uma pessoa", description = "Grava um registro no banco de dados por id",
	tags= {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	})
	public PersonVO create(@RequestBody PersonVO person) throws Exception  {
		
		return service.create(person);
	}
	
	@PostMapping(value = "/v2",produces={ MediaType.APPLICATION_JSON,
											MediaType.APPLICATION_XML,
											MediaType.APPLICATION_YML } ,
			consumes={ MediaType.APPLICATION_JSON,
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML } )
	@Operation(summary = "Grava uma pessoa", description = "Grava um registro no banco de dados por id",
	tags= {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	})
public PersonVOV2 createV2(@RequestBody PersonVOV2 person)  {
	
	return service.createV2(person);
}
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON,
								MediaType.APPLICATION_XML,
								MediaType.APPLICATION_YML } ,
				produces = { MediaType.APPLICATION_JSON,
								MediaType.APPLICATION_XML,
								MediaType.APPLICATION_YML } )
	@Operation(summary = "Atualiza uma pessoa", description = "Atualiza um registro no banco de dados por id",
	tags= {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "No content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Not found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Internal error", responseCode = "500", content = @Content)
	})
	public PersonVO update(@RequestBody PersonVO person) throws Exception {
		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Apaga uma pessoa", description = "Apaga um registro no banco de dados por id",
	tags= {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = {
							@Content(schema = @Schema(implementation = PersonVO.class))
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
