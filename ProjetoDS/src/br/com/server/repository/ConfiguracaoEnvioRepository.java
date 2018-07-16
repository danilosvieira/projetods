package br.com.server.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.server.model.ConfiguracaoEnvioModel;

@RepositoryRestResource(collectionResourceRel = "configuracao", path = "configuracoes")
public interface ConfiguracaoEnvioRepository extends CrudRepository<ConfiguracaoEnvioModel, Integer> {

	ConfiguracaoEnvioModel findById(int id);
	
}
