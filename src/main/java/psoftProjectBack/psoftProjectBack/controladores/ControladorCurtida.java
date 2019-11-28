package psoftProjectBack.psoftProjectBack.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.entidades.Curtida;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCampanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCurtida;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@Api(value = "Sistema de gerenciamento de curtida", description = "Controlador do gerenciamento de curtidas das campanhas")
@RestController
public class ControladorCurtida {

	private ServicoCurtida servicoCurtida;
	private ServicoJWT servicoJWT;
	private ServicoUsuario servicoUsuario;
	private ServicoCampanha servicoCampanha;

	public ControladorCurtida(ServicoCurtida sCurtida, ServicoJWT sJWT, ServicoUsuario sUsuario,
			ServicoCampanha sCampanha) {
		super();
		servicoCurtida = sCurtida;
		servicoJWT = sJWT;
		servicoUsuario = sUsuario;
		servicoCampanha = sCampanha;
	}

	@PostMapping("/campanha/{id}/curtir")
	public ResponseEntity<Curtida> addRemoveCurtida(@RequestBody Curtida curtida, @PathVariable("id") long id,
			@RequestHeader("Authorization") String header) throws Exception {

		String email = servicoJWT.recuperarSujeitoDoToken(header);
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<Curtida>(HttpStatus.NOT_FOUND);
		}

		Campanha campanha = servicoCampanha.acessaCampanha(id).get();
		Usuario usuario = servicoUsuario.getUsuario(email).get();

		if (servicoCampanha.jaCurtiu(campanha, usuario)) {
			return new ResponseEntity<Curtida>(this.servicoCurtida.removerCurtida(curtida), HttpStatus.OK);
		}

		curtida.setCampanha(campanha);
		curtida.setQuemDeuLike(usuario);

		return new ResponseEntity<Curtida>(servicoCurtida.adicionarCurtida(curtida), HttpStatus.CREATED);

	}

	@GetMapping("/campanha/{id}/curtir")
	public ResponseEntity<List<Curtida>> quaisCurtiram(@PathVariable("id") long id,
			@RequestHeader("Authorization") String header) throws Exception {

		Optional<Campanha> campanha = this.servicoCampanha.acessaCampanha(id);

		return new ResponseEntity<List<Curtida>>(servicoCurtida.quaisCurtiram(campanha.get()), HttpStatus.OK);

	}

}
