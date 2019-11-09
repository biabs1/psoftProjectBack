package psoftProjectBack.psoftProjectBack.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private Usuario quemComentou;
	private String texto;
	@OneToOne
	@JsonIgnore
	private Campanha campanha;

	public Comentario() {
		super();
	}

	public Comentario(int id, Usuario quemComentou, String texto, Campanha campanha) {
		super();
		this.id = id;
		this.quemComentou = quemComentou;
		this.texto = texto;
		this.campanha = campanha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}
