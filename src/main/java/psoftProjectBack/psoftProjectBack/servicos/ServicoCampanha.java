package psoftProjectBack.psoftProjectBack.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
		return campanha.getMeta() - quantiaRecebida(campanha);
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

		if (criterio.equalsIgnoreCase("data")) {
			return this.campanhasDAO.findTop10ByOrderByDeadline();
		} else if (criterio.equalsIgnoreCase("meta")) {
			return this.campanhasDAO.findTop10ByOrderByMeta();
		} else {
			return this.campanhasDAO.findTop10ByOrderByCurtidas();
		}

	}

	public List<Campanha> campanhasQueUsuarioDoou(String email) {

		List<Campanha> camps = new ArrayList<>();

		for (int i = 0; i < recuperaTodasAsCampanhas().size(); i++) {
			if (recuperaTodasAsCampanhas().get(i).getDoacoes().get(i).getQuemDoou().getEmail().equals(email)) {
				camps.add(recuperaTodasAsCampanhas().get(i));
			}
		}
		return camps;
	}

}
