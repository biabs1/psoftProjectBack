package psoftProjectBack.psoftProjectBack.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import psoftProjectBack.psoftProjectBack.enumerador.StatusCampanha;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Campanha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String nomeCurto;
	private String descricao;
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

}
