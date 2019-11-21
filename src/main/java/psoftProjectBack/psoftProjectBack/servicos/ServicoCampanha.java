package psoftProjectBack.psoftProjectBack.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.enumerador.StatusCampanha;
import psoftProjectBack.psoftProjectBack.repositorios.RepositorioCampanha;

@Service
public class ServicoCampanha {

	private RepositorioCampanha<Campanha, Long> campanhasDAO;

	public ServicoCampanha(RepositorioCampanha<Campanha, Long> campanhasDAO) {
		super();
		this.campanhasDAO = campanhasDAO;
	}

	public Campanha cadastraCampanha(Campanha campanha) {
		return this.campanhasDAO.save(campanha);
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

}
