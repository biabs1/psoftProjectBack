package psoftProjectBack.psoftProjectBack.servicos;

import java.util.Optional;

import org.springframework.stereotype.Service;

import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.repositorios.RepositorioComentario;

@Service
public class ServicoComentario {
	
	private RepositorioComentario<Comentario, Long> comentariosDAO;

	public ServicoComentario(RepositorioComentario<Comentario, Long> comentariosDAO) {
		super();
		this.comentariosDAO = comentariosDAO;
	}
	
	public Comentario adicionarComentario(Comentario comentario) {
		return this.comentariosDAO.save(comentario);
	}
	
	public Optional<Comentario> getComentario(Long id) {
		return this.comentariosDAO.findById(id);
	}

	public Comentario removerComentario(Comentario comentario) {
		comentario.setApagado(true);
		return this.comentariosDAO.save(comentario);
	}
	
	

}
