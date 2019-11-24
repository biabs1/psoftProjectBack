package psoftProjectBack.psoftProjectBack.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne
	private Usuario quemComentou;
	private String texto;
	@OneToOne
	@JsonIgnore
	private Campanha campanha;
	@OneToOne(cascade = {CascadeType.ALL})
	@JsonIgnore
	private Comentario comentario;
	private boolean apagado;

	public Comentario() {
		super();
	}

	public Comentario(int id, Usuario quemComentou, String texto, Campanha campanha, Comentario comentario) {
		super();
		this.id = id;
		this.quemComentou = quemComentou;
		this.texto = texto;
		this.campanha = campanha;
		this.comentario = comentario;
		this.apagado = false;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public boolean isApagado() {
		return apagado;
	}

	public void setApagado(boolean apagado) {
		this.apagado = apagado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Usuario getQuemComentou() {
		return quemComentou;
	}

	public void setQuemComentou(Usuario quemComentou) {
		this.quemComentou = quemComentou;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Campanha getCampanha() {
		return campanha;
	}

	public void setCampanha(Campanha campanha) {
		this.campanha = campanha;
	}

	public Comentario getComentatio() {
		return comentario;
	}

	public void setComentatio(Comentario comentario) {
		this.comentario = comentario;
	}

}
