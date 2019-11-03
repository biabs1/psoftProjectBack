package psoftProjectBack.psoftProjectBack.controladores;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@RestController
@RequestMapping("/auth")
public class ControladorLogin {
	
	private final String TOKEN_KEY = "login do batman";
	
	private ServicoUsuario servicoUsuario;
	
	public ControladorLogin(ServicoUsuario servicoUsuario) {
		super();
		this.servicoUsuario = servicoUsuario;
	}
	
	@RequestMapping("/login")
	public LoginResponse autenticar(@RequestBody Usuario usuario) throws ServletException {
		Optional<Usuario> authUsuario = servicoUsuario.recuperarUsuario(usuario.getEmail());
		
		if (!authUsuario.isPresent()) {
			throw new ServletException("Usuario nao encontrado!");
		}
		
		if (!authUsuario.get().getSenha().equals(usuario.getSenha())) {
			throw new ServletException("Senha invalida!");
		}

		String token = Jwts.builder().setSubject(authUsuario.get().getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)).compact();

		return new LoginResponse(token);

	}
	
	private class LoginResponse {
		public String token;

		public LoginResponse(String token) {
			this.token = token;
		}
	}

}
