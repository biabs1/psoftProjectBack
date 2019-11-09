package psoftProjectBack.psoftProjectBack.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
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
