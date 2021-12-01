package com.nisum.exam.dao;

import java.util.List;
import java.util.Optional;

public interface OperationsDao<Entity> {

	Entity save(Entity entity);
	
	Optional<Entity> findOne(String attributeId, String value);
	
	List<Entity> findAll();

}
