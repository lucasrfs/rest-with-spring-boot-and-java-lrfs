package br.com.lrfs.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.lrfs.data.vo.v1.PersonVO;
import br.com.lrfs.exceptions.RequiredObjectIsNullException;
import br.com.lrfs.model.Person;
import br.com.lrfs.repositories.PersonRepository;
import br.com.lrfs.services.PersonServices;
import br.com.lrfs.unittests.mapper.mocks.MockPerson;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockPerson input;
	
	@InjectMocks
	private PersonServices service;
	
	@Mock
	PersonRepository personRepository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Person> list = input.mockEntityList();
		
		when(personRepository.findAll()).thenReturn(list);
		
		var result = service.findAll();
		
		assertNotNull(result);
		assertEquals(14, result.size());
		
		
	}

	@Test
	void testFindById() throws Exception {
		Person person = input.mockEntity();
		person.setId(1L);
		
		when(personRepository.findById(1L))
			.thenReturn(Optional.of(person));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		System.out.println(result.toString());
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
	}

	@Test
	void testCreate() throws Exception {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		
		
		when(personRepository.save(entity))
			.thenReturn(entity);
		
		var result = service.create(vo);
			
			assertNotNull(result);
			assertNotNull(result.getKey());
			assertNotNull(result.getLinks());
			
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
	}
	
	@Test
	void testCreateWithNullPerson() throws Exception {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "Nao e possivel persistir um objeto nulo";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
				
	}

	@Test
	void testCreateV2() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() throws Exception {
		Person entity = input.mockEntity(1);
		Person persisted = entity;
		persisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		
		when(personRepository.findById(1L))
		.thenReturn(Optional.of(entity));
		when(personRepository.save(entity))
			.thenReturn(entity);
		
		var result = service.update(vo);
			
			assertNotNull(result);
			assertNotNull(result.getKey());
			assertNotNull(result.getLinks());
			
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
	}

	@Test
	void testDelete() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		service.delete(1L);
	}

}
