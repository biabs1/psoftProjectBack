package psoftProjectBack.psoftProjectBack.servicos;

import java.util.Optional;

import org.springframework.stereotype.Service;

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

	public void removerCurtida(Curtida curtida) {
		this.curtidasDAO.delete(curtida);
	}
	

}
