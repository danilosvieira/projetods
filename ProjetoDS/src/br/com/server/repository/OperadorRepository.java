package br.com.server.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.server.model.OperadorModel;

@RepositoryRestResource(collectionResourceRel = "operador", path = "operadores")
public interface OperadorRepository extends PagingAndSortingRepository<OperadorModel, Long> {

	OperadorModel findById(@Param("id") int id);
	
}
