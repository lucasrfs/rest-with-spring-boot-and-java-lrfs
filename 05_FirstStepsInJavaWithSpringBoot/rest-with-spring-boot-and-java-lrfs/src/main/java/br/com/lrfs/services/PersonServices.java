package br.com.lrfs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lrfs.exceptions.ResourceNotFoundException;
import br.com.lrfs.model.Person;
import br.com.lrfs.repositories.PersonRepository;

@Service
public class PersonServices {
	
	
	private Logger logger  = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll(){
		List<Person> persons = new ArrayList<>();
		
		return repository.findAll();
	}
	
	public Optional<Person> findById(Long id){		
		
		logger.info("Buscando uma pessoa");		
		return Optional.ofNullable(repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this Id")));
	}
	
	public Person create(Person person)
	{
		return repository.save(person);
	}
	
	public Person update(Person person)
	{
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Person not found"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
