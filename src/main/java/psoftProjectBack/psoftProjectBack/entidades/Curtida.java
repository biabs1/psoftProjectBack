package psoftProjectBack.psoftProjectBack.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Curtida {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne
	private Usuario quemDeuLike;
	@OneToOne //(cascade = { CascadeType.ALL })
	@JsonIgnore
	private Campanha campanha;

	public Curtida() {
		super();
	}

	public Curtida(Usuario quemDeuLike, Campanha campanha) {
		super();
		this.quemDeuLike = quemDeuLike;
		this.campanha = campanha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
