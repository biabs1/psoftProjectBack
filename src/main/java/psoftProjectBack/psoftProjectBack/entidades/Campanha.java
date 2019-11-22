package psoftProjectBack.psoftProjectBack.entidades;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import psoftProjectBack.psoftProjectBack.enumerador.StatusCampanha;

@Entity
public class Campanha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nome;
	@JsonIgnore
	private String nomeCurto;
	private String descricao;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date deadline;
	private StatusCampanha status;
	private double meta;
	@OneToMany(mappedBy = "campanha", fetch = FetchType.LAZY)
	private List<Doacao> doacoes;
	@OneToOne
	private Usuario dono;
	@OneToMany(mappedBy = "campanha", fetch = FetchType.LAZY)
	private List<Comentario> comentarios;
	@OneToMany(mappedBy = "campanha", fetch = FetchType.LAZY)
	private List<Curtida> curtidas;

	public Campanha() {
		super();
	}

	public Campanha(int id, String nome, String nomeCurto, String descricao, Date deadline, double meta,
			List<Doacao> doacoes, Usuario dono, List<Comentario> comentarios, List<Curtida> curtidas) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeCurto = nomeCurto;
		this.descricao = descricao;
		this.deadline = deadline;
		this.meta = meta;
		this.doacoes = doacoes;
		this.dono = dono;
		this.comentarios = comentarios;
		this.curtidas = curtidas;
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

	public String getNomeCurto() {
		return nomeCurto;
	}

	public void setNomeCurto(String nomeCurto) {
		this.nomeCurto = nomeCurto;
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

	public List<Doacao> getDoacoes() {
		return doacoes;
	}

	public void setDoacoes(List<Doacao> doacoes) {
		this.doacoes = doacoes;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Curtida> getCurtidas() {
		return curtidas;
	}

	public void setCurtidas(List<Curtida> curtidas) {
		this.curtidas = curtidas;
	}

}
