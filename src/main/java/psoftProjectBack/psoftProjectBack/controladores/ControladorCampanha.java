package psoftProjectBack.psoftProjectBack.controladores;

import java.util.List;

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

	@PostMapping("/campanha")
	public ResponseEntity<Campanha> cadastraCampanha(@RequestBody Campanha campanha) {
		System.out.println(campanha);
		return new ResponseEntity<Campanha>(this.servicoCampanha.cadastraCampanha(campanha), HttpStatus.CREATED);
	}

	@RequestMapping("/campanha")
	public ResponseEntity<List<Campanha>> buscarCampanha(String textoDaBusca) {

		List<Campanha> campanhaEncontrada = this.servicoCampanha.recuperaCampanha(textoDaBusca);

		return new ResponseEntity<List<Campanha>>(campanhaEncontrada, HttpStatus.OK);

	}

}
