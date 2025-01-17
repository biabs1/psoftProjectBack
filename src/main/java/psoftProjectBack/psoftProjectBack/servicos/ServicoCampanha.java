package psoftProjectBack.psoftProjectBack.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.entidades.Curtida;
import psoftProjectBack.psoftProjectBack.entidades.Doacao;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.repositorios.RepositorioCampanha;

@Service
public class ServicoCampanha {

	private RepositorioCampanha<Campanha, Long> campanhasDAO;

	public ServicoCampanha(RepositorioCampanha<Campanha, Long> campanhasDAO) {
		super();
		this.campanhasDAO = campanhasDAO;
	}

	public Campanha cadastraCampanha(Campanha campanha) throws Exception {

		Date dtConvert = java.sql.Date.valueOf(this.dataAtual());

		if (campanha.getDeadline().compareTo(dtConvert) == -1) {
			throw new Exception("O deadline deve ser a pelo menos 24h de agora");
		}

		return this.campanhasDAO.save(campanha);
	}

	public LocalDate dataAtual() {
		LocalDate dataAtual = LocalDate.now();
		return dataAtual;
	}

	public boolean nomeCurtoIgual(Campanha campanha) {
		for (Campanha c : campanhasDAO.findAll()) {
			if (c.getNomeCurto().equals(campanha.getNomeCurto()))
				return true;
		}
		return false;
	}

	public List<Campanha> recuperaCampanhas(String nome) {
		return this.campanhasDAO.findByNomeIgnoreCaseContaining(nome);
	}

	public List<Campanha> recuperaTodasAsCampanhas() {
		return this.campanhasDAO.findAll();
	}

	public List<Campanha> recuperarCampanhasPorStatus(String status) {
		List<Campanha> todasAsCampanhas = this.recuperaTodasAsCampanhas();
		List<Campanha> statuSelecionado = new ArrayList<>();
		for (Campanha c : todasAsCampanhas) {
			if (c.getStatus().equalsStatus(status)) {
				statuSelecionado.add(c);
			}
		}
		return statuSelecionado;
	}

	public Optional<Campanha> acessaCampanha(Long id) {
		return this.campanhasDAO.findById(id);
	}

	private double quantiaRecebida(Campanha campanha) {
		double quantiaRecebida = 0;
		for (Doacao d : campanha.getDoacoes()) {
			quantiaRecebida += d.getQuantiaDoada();
		}
		return quantiaRecebida;
	}

	public Double quantoFalta(Long id) {
		Campanha campanha = acessaCampanha(id).get();
		double quantoFaltaCalculado = campanha.getMeta() - quantiaRecebida(campanha);
		if (quantoFaltaCalculado > 0)
			return quantoFaltaCalculado;
		else
			return 0.0;
	}

	public boolean jaCurtiu(Campanha campanha, Usuario usuario) throws Exception {
		for (Curtida c : campanha.getCurtidas()) {
			if (c.getQuemDeuLike().equals(usuario)) {
				return true;
			}
		}
		return false;
	}

	public Integer quantidadeCurtidas(long id) {
		Campanha campanha = acessaCampanha(id).get();
		return campanha.getCurtidas().size();
	}

	public void removerCurtida(Campanha campanha, Curtida curtida) {
		List<Curtida> curtidas = campanha.getCurtidas();
		curtidas.remove(curtida);
		campanha.setCurtidas(curtidas);
	}

	public List<Comentario> listarComentarios(long id) {
		Campanha campanha = acessaCampanha(id).get();
		List<Comentario> comentarios = new ArrayList<Comentario>();
		for (Comentario c : campanha.getComentarios()) {
			if (!c.isApagado()) {
				comentarios.add(c);
			}
		}
		return comentarios;
	}

	public List<Campanha> recuperaCampanhasDoUsuario(String email) {
		List<Campanha> campUsuarios = new ArrayList<Campanha>();
		for (Campanha c : recuperaTodasAsCampanhas()) {
			if (c.getDono().getEmail().equals(email)) {
				campUsuarios.add(c);
			}
		}
		return campUsuarios;
	}

	public List<Campanha> campanhasOrdenadasTop5(String criterio) {

		List<Campanha> top5Campanhas;

		if (criterio.equalsIgnoreCase("data")) {
			top5Campanhas = this.campanhasDAO.findAll(Sort.by(Sort.Direction.ASC, "deadline"));
			return verificaTamanho(top5Campanhas);
		} else if (criterio.equalsIgnoreCase("meta")) {
			top5Campanhas = this.campanhasDAO.findAll(Sort.by(Sort.Direction.ASC, "meta"));
			return verificaTamanho(top5Campanhas);
		} else {
			top5Campanhas = this.campanhasDAO.findAll(Sort.by("curtidas").ascending());
			return verificaTamanho(top5Campanhas);
		}

	}

	private List<Campanha> verificaTamanho(List<Campanha> top5Campanhas) {
		if (top5Campanhas.size() <= 5) {
			return top5Campanhas;
		} else {
			return top5Campanhas.subList(0, 5);
		}
	}

	public List<Campanha> campanhasQueUsuarioDoou(String email) {

		List<Campanha> camps = new ArrayList<>();

		if (!recuperaTodasAsCampanhas().isEmpty()) {
			for (int i = 0; i < recuperaTodasAsCampanhas().size(); i++) {
				if (!recuperaTodasAsCampanhas().get(i).getDoacoes().isEmpty()) {
					if (recuperaTodasAsCampanhas().get(i).getDoacoes().get(i).getQuemDoou().getEmail().equals(email)) {
						camps.add(recuperaTodasAsCampanhas().get(i));
					}
				}
			}
		}
		return camps;
	}

}
