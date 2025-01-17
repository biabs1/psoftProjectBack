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
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.enumerador.StatusCampanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCampanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@Api(value="Sistema de gerenciamento de campanha", description="Controlador do sistema de gerenciamento de campanhas")
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

	@GetMapping("/")
	public String index() {
		return "";
	}

	@PostMapping("/campanha")
	public ResponseEntity<Campanha> cadastraCampanha(@RequestBody Campanha campanha,
			@RequestHeader("Authorization") String header) throws Exception {

		String email = servicoJWT.recuperarSujeitoDoToken(header);

		verificaUsuario(email);

		campanha.setDono(this.servicoUsuario.getUsuario(email).get());
		campanha.setStatus(StatusCampanha.ATIVA);

		if (servicoCampanha.nomeCurtoIgual(campanha))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Nome curto já cadastrado");

		return new ResponseEntity<Campanha>(servicoCampanha.cadastraCampanha(campanha), HttpStatus.CREATED);

	}

	@GetMapping("/campanha")
	public ResponseEntity<List<Campanha>> buscarCampanhas(@RequestParam("nome") String textoDaBusca,
			@RequestHeader("Authorization") String header) throws ServletException {
		String email = servicoJWT.recuperarSujeitoDoToken(header);
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<List<Campanha>>(HttpStatus.NOT_FOUND);
		}

		List<Campanha> campanhasEncontradas = this.servicoCampanha.recuperaCampanhas(textoDaBusca);
		return new ResponseEntity<List<Campanha>>(campanhasEncontradas, HttpStatus.OK);

	}

	@GetMapping("/campanha/{status}/buscar")
	public ResponseEntity<List<Campanha>> buscarCampanhasAtivas(@PathVariable String status) {
		return new ResponseEntity<List<Campanha>>(this.servicoCampanha.recuperarCampanhasPorStatus(status),
				HttpStatus.OK);

	}

	@GetMapping("/campanha/{id}")
	public ResponseEntity<Campanha> acessaCampanha(@PathVariable("id") long id) {
		return new ResponseEntity<Campanha>(this.servicoCampanha.acessaCampanha(id).get(), HttpStatus.OK);
	}

	@GetMapping("/campanha/{id}/quantoFalta")
	public ResponseEntity<Double> acessaQuantoFaltaCampanha(@PathVariable("id") long id) {
		return new ResponseEntity<Double>(this.servicoCampanha.quantoFalta(id), HttpStatus.OK);
	}

	@GetMapping("/campanha/{id}/quantidadeCurtidas")
	public ResponseEntity<Integer> acessaQuantidadeCurtidasCampanha(@PathVariable("id") long id) {
		return new ResponseEntity<Integer>(this.servicoCampanha.quantidadeCurtidas(id), HttpStatus.OK);
	}

	@GetMapping("/campanha/minhasCampanhas")
	public ResponseEntity<List<Campanha>> recuperaCampanhasDoUsuario(@RequestHeader("Authorization") String header)
			throws ServletException {

		String email = servicoJWT.recuperarSujeitoDoToken(header);

		verificaUsuario(email);

		return new ResponseEntity<List<Campanha>>(this.servicoCampanha.recuperaCampanhasDoUsuario(email),
				HttpStatus.OK);
	}

	@GetMapping("/campanha/ordenadas/{criterio}")
	public ResponseEntity<List<Campanha>> campanhasOrdenadasPorCriterio(@PathVariable("criterio") String criterio) {
		return new ResponseEntity<List<Campanha>>(this.servicoCampanha.campanhasOrdenadasTop5(criterio), HttpStatus.OK);
	}

	@GetMapping("/campanha/quaisDoei")
	public ResponseEntity<List<Campanha>> campanhasQueUsuarioDoou(@RequestHeader("Authorization") String header)
			throws ServletException {

		String email = this.servicoJWT.recuperarSujeitoDoToken(header);

		return new ResponseEntity<List<Campanha>>(this.servicoCampanha.campanhasQueUsuarioDoou(email), HttpStatus.OK);

	}

	private void verificaUsuario(String email) {
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado!");
		}
	}

}
