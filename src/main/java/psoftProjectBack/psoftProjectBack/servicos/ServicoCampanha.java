package psoftProjectBack.psoftProjectBack.servicos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
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

		if (dtConvert.compareTo(campanha.getDeadline()) == -1) {
			throw new Exception("A data não pode ser anterior a atual!");
		}
		if (dtConvert.compareTo(campanha.getDeadline()) == 0) {
			throw new Exception("A data não pode ser o dia atual!");
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

}
