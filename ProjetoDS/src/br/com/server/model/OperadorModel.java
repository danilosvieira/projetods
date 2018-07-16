package br.com.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Operador")
public class OperadorModel {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
	private int id;
	
	@Column(name = "nome")
	private String nome;

	@Column(name = "isAdmin")
	private String isAdmin;
		
	@OneToOne(targetEntity=ConfiguracaoEnvioModel.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "idConfiguracao", referencedColumnName = "id")
    private ConfiguracaoEnvioModel configuracaoEnvio;
	
	public OperadorModel() {

	}

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
