package psoftProjectBack.psoftProjectBack.controladores;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.enumerador.StatusCampanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCampanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@RestController
public class ControladorCampanha {

	private ServicoCampanha servicoCampanha;
	private ServicoUsuario servicoUsuario;
	private ServicoJWT servicoJWT;

	public ControladorCampanha(ServicoCampanha sCampanha, ServicoUsuario sUsuario, ServicoJWT sJwt) {
		super();
		this.servicoUsuario = sUsuario;
		this.servicoJWT = sJwt;
		this.servicoCampanha = sCampanha;
	}

	@PostMapping("/campanha")
	public ResponseEntity<Campanha> cadastraCampanha(@RequestBody Campanha campanha,
			@RequestHeader("Authorization") String header) throws Exception {

		String email = servicoJWT.recuperarSujeitoDoToken(header);
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<Campanha>(HttpStatus.NOT_FOUND);
		}

		campanha.setDono(this.servicoUsuario.getUsuario(email).get());
		campanha.setStatus(StatusCampanha.ATIVA);

		// if (servicoCampanha.nomeCurtoIgual(campanha))
		// throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nome curto já
		// cadastrado");

		return new ResponseEntity<Campanha>(servicoCampanha.cadastraCampanha(campanha), HttpStatus.CREATED);

	}

	@GetMapping("/campanha")
	public ResponseEntity<List<Campanha>> buscarCampanhas(@RequestParam("nome") String textoDaBusca) {

		List<Campanha> campanhasEncontradas = this.servicoCampanha.recuperaCampanhas(textoDaBusca);

		return new ResponseEntity<List<Campanha>>(campanhasEncontradas, HttpStatus.OK);

	}

	@GetMapping("/campanha/{status}")
	public ResponseEntity<List<Campanha>> buscarCampanhasAtivas(@PathVariable String status) {

		return new ResponseEntity<List<Campanha>>(this.servicoCampanha.recuperarCampanhasPorStatus(status),
				HttpStatus.OK);

	}

	@GetMapping("/campanha/{id}")
	public ResponseEntity<Campanha> acessaCampanha(@PathVariable Long id) {

		return new ResponseEntity<Campanha>(this.servicoCampanha.acessaCampanha(id).get(), HttpStatus.OK);

	}

}
