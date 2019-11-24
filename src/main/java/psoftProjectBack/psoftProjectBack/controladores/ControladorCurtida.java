package psoftProjectBack.psoftProjectBack.controladores;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.entidades.Curtida;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCampanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCurtida;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@RestController
public class ControladorCurtida {
	
	private ServicoCurtida servicoCurtida;
	private ServicoJWT servicoJWT;
	private ServicoUsuario servicoUsuario;
	private ServicoCampanha servicoCampanha;
	
	public ControladorCurtida(ServicoCurtida sCurtida, ServicoJWT sJWT,
			ServicoUsuario sUsuario, ServicoCampanha sCampanha) {
		super();
		servicoCurtida = sCurtida;
		servicoJWT = sJWT;
		servicoUsuario = sUsuario;
		servicoCampanha = sCampanha;
	}
	
	@PostMapping("/campanha/{id}/curtir")
	public ResponseEntity<Curtida> addRemoveCurtida(@RequestBody Curtida curtida,
			@PathVariable("id") long id, 
			@RequestHeader("Authorization") String header) throws Exception {
		
		String email = servicoJWT.recuperarSujeitoDoToken(header);
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<Curtida>(HttpStatus.NOT_FOUND);
		}
		
		Campanha campanha = servicoCampanha.acessaCampanha(id).get();
		Usuario usuario = servicoUsuario.getUsuario(email).get();
		
		if (servicoCampanha.jaCurtiu(campanha, usuario)) {
			//servicoCampanha.removerCurtida(campanha, curtida);
			servicoCurtida.removerCurtida(curtida);
			return new ResponseEntity<Curtida>(curtida, HttpStatus.OK);
			//throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Você já curtiu esta campanha");
		}
		
		curtida.setCampanha(campanha);
		curtida.setQuemDeuLike(usuario);
		
		return new ResponseEntity<Curtida>(servicoCurtida.adicionarCurtida(curtida), HttpStatus.CREATED);

	}

}
