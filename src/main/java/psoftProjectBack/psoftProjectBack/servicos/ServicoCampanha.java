package psoftProjectBack.psoftProjectBack.servicos;

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

	public Optional<Campanha> recuperaCampanha(String textoDaBusca) {
		return null;
	}
	
	public String defineNomeCurto(String texto) {
		
		String nomeCurto = "";
		
		texto = texto.replace(" ", "-");
		
		
		return null;
		
	}

}
