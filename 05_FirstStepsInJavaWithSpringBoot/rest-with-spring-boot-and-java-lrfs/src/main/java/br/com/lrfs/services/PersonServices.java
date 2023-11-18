package br.com.lrfs.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.lrfs.controllers.PersonController;
import br.com.lrfs.data.vo.v1.PersonVO;
import br.com.lrfs.data.vo.v2.PersonVOV2;
import br.com.lrfs.exceptions.RequiredObjectIsNullException;
import br.com.lrfs.exceptions.ResourceNotFoundException;
import br.com.lrfs.mapper.DozerMapper;
import br.com.lrfs.mapper.custom.PersonMapper;
import br.com.lrfs.model.Person;
import br.com.lrfs.repositories.PersonRepository;

@Service
public class PersonServices {
	
	
	private Logger logger  = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
		
	public List<PersonVO> findAll(){
		
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons
			.stream()
			.forEach(p -> {
				try {
					p.add(linkTo(methodOn(PersonController.class).findByid(p.getKey())).withSelfRel());
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			});
		return persons;
	}
	
	public PersonVO findById(Long id) throws Exception{		
		
		logger.info("Buscando uma pessoa");		
		var entity = repository.findById(id).
					orElseThrow(() -> new ResourceNotFoundException("No records found"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findByid(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) throws Exception
	{
		if(person == null) throw new RequiredObjectIsNullException();
		var entity = DozerMapper.parseObject(person, Person.class);
		var ret = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		ret.add(linkTo(methodOn(PersonController.class).findByid(ret.getKey())).withSelfRel());
		return ret;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person)
	{
		var entity = mapper.convertVoToEntity(person);
		var ret = mapper.convertEntityToVo(repository.save(entity));
		return ret;
	}
	
	public PersonVO update(PersonVO person) throws Exception
	{
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("PersonVO not found"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		
		var ret = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		ret.add(linkTo(methodOn(PersonController.class).findByid(ret.getKey())).withSelfRel());
		return ret;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
