package psoftProjectBack.psoftProjectBack.controladores;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCampanha;

@RestController
public class ControladorCampanha {

	private ServicoCampanha servicoCampanha;

	public ControladorCampanha(ServicoCampanha sCampanha) {
		super();
		this.servicoCampanha = sCampanha;
	}

	@PostMapping()
	public ResponseEntity<Campanha> cadastraCampanha(@RequestBody Campanha campanha) {
		return new ResponseEntity<Campanha>(this.servicoCampanha.cadastraCampanha(campanha), HttpStatus.CREATED);
	}

	@RequestMapping()
	public ResponseEntity<Campanha> buscarCampanha(String textoDaBusca) {

		Optional<Campanha> campanhaEncontrada = this.servicoCampanha.recuperaCampanha(textoDaBusca);

		if (campanhaEncontrada.isPresent()) {
			return new ResponseEntity<Campanha>(campanhaEncontrada.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Campanha nao encontrada");
		}

	}

}
