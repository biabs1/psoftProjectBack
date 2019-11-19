package psoftProjectBack.psoftProjectBack.controladores;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
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
			@RequestHeader("Authorization") String header) throws ServletException {

		String email = servicoJWT.recuperarSujeitoDoToken(header);

		campanha.setDono(this.servicoUsuario.getUsuario(email).get());

		if (servicoCampanha.nomeCurtoIgual(campanha))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nome curto já cadastrado");

		return new ResponseEntity<Campanha>(servicoCampanha.cadastraCampanha(campanha), HttpStatus.CREATED);

	}

	@RequestMapping("/campanha")
	public ResponseEntity<List<Campanha>> buscarCampanhas(String textoDaBusca) {

		List<Campanha> campanhaEncontrada = this.servicoCampanha.recuperaCampanhas(textoDaBusca);

		return new ResponseEntity<List<Campanha>>(campanhaEncontrada, HttpStatus.OK);

	}

	@RequestMapping("/campanha/{id}")
	public ResponseEntity<Campanha> recuperaCampanha(@PathVariable long id) {
		Optional<Campanha> campanhaRecuperada = this.servicoCampanha.recuperaCampanha(id);
		if (campanhaRecuperada.isPresent()) {
			return new ResponseEntity<Campanha>(campanhaRecuperada.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campanha nao encontrada");
		}

	}

	@RequestMapping()
	public ResponseEntity<List<Campanha>> listarTodasAsCampanhas() {
		return new ResponseEntity<List<Campanha>>(this.servicoCampanha.listaCampanhas(), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Campanha> alteraDescricao(@PathVariable long id, String novaDescricao) {

		return new ResponseEntity<Campanha>(this.servicoCampanha.alteraDescricao(id, novaDescricao), HttpStatus.OK);

	}

}
