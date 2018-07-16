package br.com.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.server.model.OperadorModel;

@RepositoryRestResource(collectionResourceRel = "OperadorRepository", path = "operadores")
public interface OperadorRepository extends CrudRepository<OperadorModel, Integer>{
	
	OperadorModel findById(Long id);
	
}