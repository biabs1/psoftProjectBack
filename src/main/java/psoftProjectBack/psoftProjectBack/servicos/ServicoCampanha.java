package psoftProjectBack.psoftProjectBack.servicos;

import java.util.List;

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

	public List<Campanha> recuperaCampanhas(String textoDaBusca) {
		return this.campanhasDAO.findByNomeContaining(textoDaBusca);
	}

}
