package psoftProjectBack.psoftProjectBack.servicos;

import java.util.Optional;

import org.springframework.stereotype.Service;

import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.repositorios.RepositorioUsuario;

@Service
public class ServicoUsuario {
	
	private RepositorioUsuario<Usuario, String> usuariosDAO;
	
	public ServicoUsuario(RepositorioUsuario<Usuario, String> usuariosDAO) {
		super();
		this.usuariosDAO = usuariosDAO;
	}
	
	public Usuario adicionarUsuario(Usuario usuario) {
		return usuariosDAO.save(usuario);
	}
	
	public Optional<Usuario> recuperarUsuario(String email) {
		return usuariosDAO.findById(email);
	}

	public Optional<Usuario> getUsuario(String email) {
		return this.usuariosDAO.findById(email);
	}

}
