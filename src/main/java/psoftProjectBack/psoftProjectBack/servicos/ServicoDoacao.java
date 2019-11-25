package psoftProjectBack.psoftProjectBack.servicos;

import org.springframework.stereotype.Service;

import psoftProjectBack.psoftProjectBack.entidades.Doacao;
import psoftProjectBack.psoftProjectBack.repositorios.RepositorioDoacao;

@Service
public class ServicoDoacao {

	private RepositorioDoacao<Doacao, Long> doacoesDAO;

	public ServicoDoacao(RepositorioDoacao<Doacao, Long> doacoesDAO) {
		this.doacoesDAO = doacoesDAO;
	}

	public Doacao realizaDoacao(Doacao doacao) {
		return this.doacoesDAO.save(doacao);
	}

}
