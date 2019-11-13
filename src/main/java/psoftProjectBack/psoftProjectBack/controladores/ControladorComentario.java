package psoftProjectBack.psoftProjectBack.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

public class ControladorComentario {
	
	private ServicoUsuario servicoUsuario;
	private ServicoJWT servicoJWT;

	public ControladorComentario(ServicoUsuario servicoUsuario, ServicoJWT servicoJWT) {
		super();
		this.servicoUsuario = servicoUsuario;
		this.servicoJWT = servicoJWT;
	}

	@PostMapping("/auth/usuario/{email}")
	public ResponseEntity<Comentario> addComentario(@RequestBody Comentario comentario,
			@PathVariable String email, @RequestHeader("Authorization") String header) {
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		
		try {
			if (servicoJWT.usuarioTemPermissao(header, email)) {
				//return new ResponseEntity<Comentario>()
			}
		} catch (Exception e) {
			return new ResponseEntity<Comentario>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Comentario>(HttpStatus.UNAUTHORIZED);
	}

}