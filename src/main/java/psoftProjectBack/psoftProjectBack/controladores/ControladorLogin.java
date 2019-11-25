package psoftProjectBack.psoftProjectBack.controladores;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@RestController
@RequestMapping("/auth")
public class ControladorLogin {

	private ServicoUsuario servicoUsuario;

	private ServicoJWT servicoJwt;

	public ControladorLogin(ServicoUsuario servicoUsuario, ServicoJWT sJwt) {
		super();
		this.servicoJwt = sJwt;
		this.servicoUsuario = servicoUsuario;
	}

	@RequestMapping("/login")
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {
		
		Optional<Usuario> authUsuario = servicoUsuario.getUsuario(usuario.getEmail());

		if (!authUsuario.isPresent()) {
			throw new ServletException("Usuario nao encontrado!");
		}

		if (!authUsuario.get().getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Senha invalida!");
		}

		String tokenGerado = this.servicoJwt.geraToken(usuario.getEmail());

		return new LoginResponse(tokenGerado);

	}

	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;
		}
	}

}
