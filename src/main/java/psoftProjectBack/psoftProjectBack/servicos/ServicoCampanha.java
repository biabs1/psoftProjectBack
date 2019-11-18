package psoftProjectBack.psoftProjectBack.servicos;

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

	public List<Campanha> recuperaCampanhas(String textoDaBusca) {
		return this.campanhasDAO.findByNomeContaining(textoDaBusca);
	}

	public Campanha alteraDescricao(long id, String novaDescricao) {
		Optional<Campanha> campanha = recuperaCampanha(id);
		if (campanha.isPresent()) {
			campanha.get().setDescricao(novaDescricao);
			return campanha.get();
		} else {
			return null;
		}
	}

	public Optional<Campanha> recuperaCampanha(long id) {
		return this.campanhasDAO.findById(id);
	}

	public List<Campanha> listaCampanhas() {
		return this.campanhasDAO.findAll();
	}

}
