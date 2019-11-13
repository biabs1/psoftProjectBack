package psoftProjectBack.psoftProjectBack.servicos;

import java.util.Optional;

import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.repositorios.RepositorioComentario;

public class ServicoComentario {
	
	private RepositorioComentario<Comentario, Long> comentariosDAO;

	public ServicoComentario(RepositorioComentario<Comentario, Long> comentariosDAO) {
		super();
		this.comentariosDAO = comentariosDAO;
	}
	
	public Comentario adicionarComentario(Comentario comentario) {
		return comentariosDAO.save(comentario);
	}
	
	public Optional<Comentario> getComentario(Long id) {
		return this.comentariosDAO.findById(id);
	}

}
