package br.com.server.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class EnvioModel {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)   
	private int id;
	
	@OneToOne(targetEntity=OperadorModel.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "idOperador", referencedColumnName = "id")
	private OperadorModel operador;
	
	@OneToOne(targetEntity=ConfiguracaoEnvioModel.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "idConfiguracao", referencedColumnName = "id")
	private ConfiguracaoEnvioModel configuracao;
	
	@OneToOne(targetEntity=TipoArquivoModel.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "idTipoArquivo", referencedColumnName = "id")
	private TipoArquivoModel tipoArquivo;
	
	private Date data;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OperadorModel getOperador() {
		return operador;
	}

	public void setOperador(OperadorModel operador) {
		this.operador = operador;
	}

	public ConfiguracaoEnvioModel getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(ConfiguracaoEnvioModel configuracao) {
		this.configuracao = configuracao;
	}

	public TipoArquivoModel getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(TipoArquivoModel tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
