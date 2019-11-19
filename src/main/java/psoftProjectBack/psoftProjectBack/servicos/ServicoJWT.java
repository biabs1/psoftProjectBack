package psoftProjectBack.psoftProjectBack.servicos;

import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.filtros.FiltroToken;

@Service
public class ServicoJWT {

	private ServicoUsuario servicoUsuario;

	private final String TOKEN_KEY = "minha_senha";

	public ServicoJWT(ServicoUsuario servicoUsuario) {
		super();
		this.servicoUsuario = servicoUsuario;
	}

	public String geraToken(String email) {
		String token = Jwts.builder().setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 1000)).compact();
		return token;
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

	public boolean usuarioDonoComentario(String authorizationHeader, Comentario comentario) throws ServletException {
		String sujeito = recuperarSujeitoDoToken(authorizationHeader);
		Optional<Usuario> optUsuario = servicoUsuario.getUsuario(sujeito);
		return optUsuario.get().getEmail().equals(comentario.getQuemComentou().getEmail());

	}

	public String recuperarSujeitoDoToken(String authorizationHeader) throws ServletException {
		
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		// Extraindo apenas o token do cabecalho.
		String token = authorizationHeader.substring(FiltroToken.TOKEN_INDEX);

		String subject = null;
		
		try {
			subject = Jwts.parser().setSigningKey("minha_senha").parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new ServletException("Token invalido ou expirado!");
		}
		return subject;
	}

}
