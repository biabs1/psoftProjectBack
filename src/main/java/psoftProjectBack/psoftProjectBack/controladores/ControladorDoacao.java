package psoftProjectBack.psoftProjectBack.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import psoftProjectBack.psoftProjectBack.entidades.Doacao;
import psoftProjectBack.psoftProjectBack.servicos.ServicoDoacao;

@RestController
public class ControladorDoacao {

	private ServicoDoacao servicoDoacao;

	public ControladorDoacao(ServicoDoacao sDoacao) {
		this.servicoDoacao = sDoacao;
	}

	@PostMapping("/campanha/{id}/doacao")
	public ResponseEntity<Doacao> realizarDoacao(@RequestBody Doacao doacao, @PathVariable("id") long id,
			@RequestHeader("Authorization") String header) {
		return new ResponseEntity<Doacao>(this.servicoDoacao.realizaDoacao(doacao), HttpStatus.CREATED);

	}

}
