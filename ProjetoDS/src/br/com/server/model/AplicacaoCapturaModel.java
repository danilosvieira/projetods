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
import javax.persistence.Table;

@Entity
@Table(name="AplicacaoCaptura")
public class AplicacaoCapturaModel {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
	private int id;
	
	private String nome;
	
	@OneToMany(cascade=CascadeType.ALL)  
    @JoinTable(name="ConfiguracaoAplicacaoCaptura",  
              joinColumns={@JoinColumn(name="idAplicacaoCaptura", 
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

}
