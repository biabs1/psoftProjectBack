package psoftProjectBack.psoftProjectBack.servicos;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;

@Service
public class ServicoJWT {
	private ServicoUsuario servicoUsuario;
	
	public ServicoJWT(ServicoUsuario servicoUsuario) {
		super();
		this.servicoUsuario = servicoUsuario;
	}
	
	public boolean usuarioExiste(String authorizationHeader) throws ServletException {
		String email = recuperarSujeitoDoToken(authorizationHeader);
		return servicoUsuario.getUsuario(email).isPresent();
	}
	
	public boolean usuarioTemPermissao(String authorizationHeader, String email) throws ServletException {
		String sujeito = recuperarSujeitoDoToken(authorizationHeader);

		Optional<Usuario> optUsuario = servicoUsuario.getUsuario(sujeito);
		return optUsuario.isPresent() && optUsuario.get().getEmail().equals(email);
	}

	private String recuperarSujeitoDoToken(String authorizationHeader) throws ServletException {
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		// Extraindo apenas o token do cabecalho.
		String token = authorizationHeader.substring(
				psoftProjectBack.psoftProjectBack.filtros.FiltroToken.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey("login do batman").parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ServletException("Token invalido ou expirado!");
		}
		return subject;
	}

}
