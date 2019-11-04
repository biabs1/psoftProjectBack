package psoftProjectBack.psoftProjectBack.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import psoftProjectBack.psoftProjectBack.enumerador.StatusCampanha;

@Entity
public class Campanha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;
	private String descricao;
	private Date deadline;
	private StatusCampanha status;
	private double meta;
	private List<Doacao> doacoes;
	private Usuario dono;
	private List<String> comentarios;
	private List<Usuario> likes;

	public Campanha(int id, String nome, String descricao, Date deadline, StatusCampanha status, double meta,
			List<Doacao> doacoes, Usuario dono, List<String> comentarios, List<Usuario> likes) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.deadline = deadline;
		this.status = status;
		this.meta = meta;
		this.doacoes = doacoes;
		this.dono = dono;
		this.comentarios = comentarios;
		this.likes = likes;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public StatusCampanha getStatus() {
		return status;
	}

	public void setStatus(StatusCampanha status) {
		this.status = status;
	}

	public double getMeta() {
		return meta;
	}

	public void setMeta(double meta) {
		this.meta = meta;
	}

	public List<String> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<String> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Usuario> getLikes() {
		return likes;
	}

	public void setLikes(List<Usuario> likes) {
		this.likes = likes;
	}

	public List<Doacao> getDoacoes() {
		return doacoes;
	}

}
