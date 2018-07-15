package br.com.server.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class TipoArquivoModel {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
	private int id;
	
	private String extensao;
	
	@OneToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="ConfiguracaoAplicacaoCaptura",  
               joinColumns={@JoinColumn(name="idTipoArquivo", 
               referencedColumnName="id")},  
               inverseJoinColumns={@JoinColumn(name="idConfiguracao", 
               referencedColumnName="id")})  
    private List<ConfiguracaoEnvioModel> configuracoesEnvio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

}
