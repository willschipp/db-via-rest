package com.example.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

public interface PersonRepository extends JpaRepository<Person, Integer> {

	@RestResource(exported=false)
	@Override
	default void deleteById(Integer id) { }

	@RestResource(exported=false)
	@Override
	default void delete(Person entity) { }

	@RestResource(exported=false)
	@Override
	default void deleteAll(Iterable<? extends Person> entities) { }

	@RestResource(exported=false)
	@Override
	default void deleteAll() { }

	@RestResource(exported=false)
	@Override
	default void deleteInBatch(Iterable<Person> entities) { }

	@RestResource(exported=false)
	@Override
	default void deleteAllInBatch() { }
	
	
}
