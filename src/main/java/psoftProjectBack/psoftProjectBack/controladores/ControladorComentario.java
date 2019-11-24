package psoftProjectBack.psoftProjectBack.controladores;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCampanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoComentario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@RestController
public class ControladorComentario {
	
	private ServicoComentario servicoComentario;
	private ServicoUsuario servicoUsuario;
	private ServicoJWT servicoJWT;
	private ServicoCampanha servicoCampanha;

	public ControladorComentario(ServicoComentario sComentario, ServicoUsuario sUsuario,
			ServicoCampanha sCampanha, ServicoJWT sJWT) {
		super();
		this.servicoComentario = sComentario; 
		this.servicoUsuario = sUsuario;
		this.servicoJWT = sJWT;
		this.servicoCampanha = sCampanha;
	}

	@PostMapping("/campanha/{id}/comentar")
	public ResponseEntity<Comentario> addComentario(@RequestBody Comentario comentario, 
			@PathVariable("id") long id,
			@RequestHeader("Authorization") String header) throws ServletException {
		
		String email = servicoJWT.recuperarSujeitoDoToken(header);
		
		if (!servicoUsuario.getUsuario(email).isPresent()) {	
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		
		Campanha campanha = servicoCampanha.acessaCampanha(id).get();
		
		comentario.setCampanha(campanha);
		comentario.setQuemComentou(this.servicoUsuario.getUsuario(email).get());
		
		return new ResponseEntity<Comentario>(servicoComentario.adicionarComentario(comentario),
						HttpStatus.CREATED);
	}
	
	@DeleteMapping("/campanha/comentario/{id}/deleta")
	public ResponseEntity<Comentario> removeComentario(@PathVariable("id") long id,
			@RequestHeader("Authorization") String header) throws ServletException {
		
		String email = servicoJWT.recuperarSujeitoDoToken(header);
		
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);
		}
		
		Comentario comentario = servicoComentario.getComentario(id).get();
		
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
	
	@GetMapping("/campanha/{id}/comentario/listar")
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") long id,
			@RequestHeader("Authorization") String header) {
		return new ResponseEntity<List<Comentario>>(servicoCampanha.listarComentarios(id), HttpStatus.OK);
	}

}
