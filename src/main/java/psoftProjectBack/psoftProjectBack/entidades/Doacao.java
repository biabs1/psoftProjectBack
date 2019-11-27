package psoftProjectBack.psoftProjectBack.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Doacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne
	private Usuario quemDoou;
	private double quantiaDoada;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dataDaDoacao;
	@OneToOne
	@JsonIgnore
	private Campanha campanha;

	public Doacao() {
		super();
	}

	public Doacao(long id, Usuario quemDoou, double quantiaDoada, Date dataDaDoacao, Campanha campanha) {
		super();
		this.id = id;
		this.quemDoou = quemDoou;
		this.quantiaDoada = quantiaDoada;
		this.dataDaDoacao = dataDaDoacao;
		this.campanha = campanha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getQuemDoou() {
		return quemDoou;
	}

	public void setQuemDoou(Usuario quemDoou) {
		this.quemDoou = quemDoou;
	}

	public double getQuantiaDoada() {
		return quantiaDoada;
	}

	public void setQuantiaDoada(double quantiaDoada) {
		this.quantiaDoada = quantiaDoada;
	}

	public Date getDataDaDoacao() {
		return dataDaDoacao;
	}

	public void setDataDaDoacao(Date dataDaDoacao) {
		this.dataDaDoacao = dataDaDoacao;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

}
