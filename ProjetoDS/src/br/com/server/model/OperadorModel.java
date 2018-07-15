package br.com.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OperadorModel {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
	private int id;
	
	private String nome;
	
	private String isAdmin;
	
	@OneToOne(targetEntity=ConfiguracaoEnvioModel.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "idConfiguracao", referencedColumnName = "id")
    private ConfiguracaoEnvioModel configuracaoEnvio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public ConfiguracaoEnvioModel getConfiguracaoEnvio() {
		return configuracaoEnvio;
	}

	public void setConfiguracaoEnvio(ConfiguracaoEnvioModel configuracaoEnvio) {
		this.configuracaoEnvio = configuracaoEnvio;
	}
	
}
