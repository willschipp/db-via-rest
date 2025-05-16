package com.github.willschipp.model;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Profile("server")
@Repository
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

	// @RestResource(exported=false)
	// @Override
	// default void deleteAllInBatch() { }
	
	
}
