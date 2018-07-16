package br.com.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.server.model.EnvioModel;

@RepositoryRestResource(collectionResourceRel = "envio", path = "envios")
public interface EnvioRepository extends CrudRepository<EnvioModel, Integer> {

	EnvioModel findById(int id);
	
}
