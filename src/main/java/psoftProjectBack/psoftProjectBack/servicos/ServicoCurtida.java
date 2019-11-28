package psoftProjectBack.psoftProjectBack.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.entidades.Curtida;
import psoftProjectBack.psoftProjectBack.repositorios.RepositorioCurtida;

@Service
public class ServicoCurtida {

	private RepositorioCurtida<Curtida, Long> curtidasDAO;

	public ServicoCurtida(RepositorioCurtida<Curtida, Long> curtidasDAO) {
		super();
		this.curtidasDAO = curtidasDAO;
	}

	public Curtida adicionarCurtida(Curtida curtida) {
		return this.curtidasDAO.save(curtida);
	}

	public Optional<Curtida> getCurtida(Long id) {
		return this.curtidasDAO.findById(id);
	}

	public Curtida removerCurtida(Curtida curtida) {
		this.curtidasDAO.delete(curtida);
		return this.curtidasDAO.save(curtida);
	}

	public List<Curtida> quaisCurtiram(Campanha campanha) {

		List<Curtida> curtidasDaCampanha = new ArrayList<>();

		for (Curtida c : this.curtidasDAO.findAll()) {
			if (c.getCampanha().getId() == campanha.getId()) {
				curtidasDaCampanha.add(c);
			}
		}

		return curtidasDaCampanha;
	}

}
