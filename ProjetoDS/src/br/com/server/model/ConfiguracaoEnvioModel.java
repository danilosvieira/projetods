package br.com.server.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ConfiguracaoEnvio")
public class ConfiguracaoEnvioModel {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)   
	private int id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ConfiguracaoTipoArquivo",
    		   joinColumns={@JoinColumn(name="idConfiguracao",  
    		   referencedColumnName="id")},  
    		   inverseJoinColumns={@JoinColumn(name="idTipoArquivo",   
    		   referencedColumnName="id")})
    private List<TipoArquivoModel> tiposArquivo;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="ConfiguracaoAplicacaoCaptura",
    		   joinColumns={@JoinColumn(name="idConfiguracao",  
    		   referencedColumnName="id")},  
    		   inverseJoinColumns={@JoinColumn(name="idAplicacaoCaptura",   
    		   referencedColumnName="id")})
    private List<AplicacaoCapturaModel> aplicacoesCaptura;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<TipoArquivoModel> getTiposArquivo() {
		return tiposArquivo;
	}

	public void setTiposArquivo(List<TipoArquivoModel> tiposArquivo) {
		this.tiposArquivo = tiposArquivo;
	}

	public List<AplicacaoCapturaModel> getAplicacoesCaptura() {
		return aplicacoesCaptura;
	}

	public void setAplicacoesCaptura(List<AplicacaoCapturaModel> aplicacoesCaptura) {
		this.aplicacoesCaptura = aplicacoesCaptura;
	}

		
}
