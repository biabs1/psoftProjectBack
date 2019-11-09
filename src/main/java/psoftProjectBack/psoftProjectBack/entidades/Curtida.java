package psoftProjectBack.psoftProjectBack.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Curtida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private Usuario quemDeuLike;
	@OneToOne
	private Campanha campanha;

	public Curtida() {
		super();
	}

	public Curtida(int id, Usuario quemDeuLike, Campanha campanha) {
		super();
		this.id = id;
		this.quemDeuLike = quemDeuLike;
		this.campanha = campanha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getQuemDeuLike() {
		return quemDeuLike;
	}

	public void setQuemDeuLike(Usuario quemDeuLike) {
		this.quemDeuLike = quemDeuLike;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

}
