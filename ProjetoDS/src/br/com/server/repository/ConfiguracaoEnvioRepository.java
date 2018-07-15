package br.com.server.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.server.model.ConfiguracaoEnvioModel;

@RepositoryRestResource(collectionResourceRel = "configuracao", path = "configuracoes")
public interface ConfiguracaoEnvioRepository extends PagingAndSortingRepository<ConfiguracaoEnvioModel, Long> {

	ConfiguracaoEnvioModel findById(@Param("id") int id);
	
}
