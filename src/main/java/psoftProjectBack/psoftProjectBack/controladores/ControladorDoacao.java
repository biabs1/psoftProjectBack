package psoftProjectBack.psoftProjectBack.controladores;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import psoftProjectBack.psoftProjectBack.entidades.Campanha;
import psoftProjectBack.psoftProjectBack.entidades.Doacao;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoCampanha;
import psoftProjectBack.psoftProjectBack.servicos.ServicoDoacao;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@Api(value="Sistema de gerenciamento de doacoes", description="Controlador do gerenciamento de doacoes das campanhas")
@RestController
public class ControladorDoacao {

	private ServicoDoacao servicoDoacao;
	private ServicoJWT servicoJwt;
	private ServicoUsuario servicoUsuario;
	private ServicoCampanha servicoCampanha;

	public ControladorDoacao(ServicoDoacao sDoacao, ServicoJWT sJwt, ServicoUsuario sUsuario,
			ServicoCampanha sCampanha) {
		super(); 
		this.servicoDoacao = sDoacao;
		this.servicoJwt = sJwt;
		this.servicoUsuario = sUsuario;
		this.servicoCampanha = sCampanha;
	}

	@PostMapping("/campanha/{id}/doacao")
	public ResponseEntity<Doacao> realizarDoacao(@RequestBody Doacao doacao, @PathVariable("id") long id,
			@RequestHeader("Authorization") String header) throws ServletException {

		String email = this.servicoJwt.recuperarSujeitoDoToken(header);

		Optional<Usuario> user = this.servicoUsuario.getUsuario(email);

		doacao.setQuemDoou(user.get());

		Optional<Campanha> camp = this.servicoCampanha.acessaCampanha(id);

		doacao.setCampanha(camp.get());
		
		Date data = new Date(System.currentTimeMillis());

		doacao.setDataDaDoacao(data);

		return new ResponseEntity<Doacao>(this.servicoDoacao.realizaDoacao(doacao), HttpStatus.CREATED);

	}

}
