package psoftProjectBack.psoftProjectBack.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoComentario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

public class ControladorComentario {
	
	private ServicoComentario servicoComentario;
	private ServicoUsuario servicoUsuario;
	private ServicoJWT servicoJWT;

	public ControladorComentario(ServicoComentario sComentario, ServicoUsuario servicoUsuario, ServicoJWT servicoJWT) {
		super();
		this.servicoComentario = sComentario; 
		this.servicoUsuario = servicoUsuario;
		this.servicoJWT = servicoJWT;
	}

	@PostMapping("/auth/usuario")
	public ResponseEntity<Comentario> addComentario(@RequestBody Comentario comentario,
			@RequestHeader("Authorization") String header) {
		String email = comentario.getQuemComentou().getEmail();
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		
		try {
			if (servicoJWT.usuarioTemPermissao(header, email)) {
				return new ResponseEntity<Comentario>(servicoComentario.adicionarComentario(comentario),
						HttpStatus.CREATED);
			}
		} catch (Exception e) {
			return new ResponseEntity<Comentario>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
	}
	
	@DeleteMapping("/auth/usuario")
	public ResponseEntity<Comentario> removeComentario(@RequestBody Comentario comentario,
			@RequestHeader("Authorization") String header) {
		String email = comentario.getQuemComentou().getEmail();
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		
		try {
			if (servicoJWT.usuarioTemPermissao(header, email) &&
					servicoJWT.usuarioDonoComentario(header, comentario)) {
				return new ResponseEntity<Comentario>(servicoComentario.removerComentario(comentario),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<Comentario>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
	}

}
