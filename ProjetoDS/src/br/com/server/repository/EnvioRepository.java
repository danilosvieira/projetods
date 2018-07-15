package br.com.server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.server.model.EnvioModel;

@RepositoryRestResource(collectionResourceRel = "envio", path = "envios")
public interface EnvioRepository extends PagingAndSortingRepository<EnvioModel, Long> {

	EnvioModel findById(@Param("id") int id);
	
}
