package psoftProjectBack.psoftProjectBack.entidades;

import java.util.Date;

import javax.persistence.OneToOne;

public class Doacao {

	private Usuario quemDoou;
	private double quantiaDoada;
	private Date dataDaDoacao;
	@OneToOne
	private Campanha campanha;

	public Doacao(Usuario quemDoou, double quantiaDoada, Date dataDaDoacao) {
		super();
		this.quemDoou = quemDoou;
		this.quantiaDoada = quantiaDoada;
		this.dataDaDoacao = dataDaDoacao;
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

}
