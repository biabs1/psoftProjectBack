package psoftProjectBack.psoftProjectBack.controladores;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoComentario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

public class ControladorComentario {
	
	private ServicoComentario servicoComentario;
	private ServicoUsuario servicoUsuario;
	private ServicoJWT servicoJWT;

	public ControladorComentario(ServicoComentario sComentario, ServicoUsuario sUsuario, ServicoJWT sJWT) {
		super();
		this.servicoComentario = sComentario; 
		this.servicoUsuario = sUsuario;
		this.servicoJWT = sJWT;
	}

	@PostMapping("/comentario")
	public ResponseEntity<Comentario> addComentario(@RequestBody Comentario comentario, Campanha campanha,
			@RequestHeader("Authorization") String header) throws ServletException {
		
		String email = servicoJWT.recuperarSujeitoDoToken(header);
		
		if (!servicoUsuario.getUsuario(email).isPresent()) {	
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		
		comentario.setCampanha(campanha);
		
		comentario.setQuemComentou(this.servicoUsuario.getUsuario(email).get());
		
		return new ResponseEntity<Comentario>(servicoComentario.adicionarComentario(comentario),
						HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comentario/deleta")
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
