package psoftProjectBack.psoftProjectBack.filtros;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class FiltroToken extends GenericFilterBean {
	
	public final static int TOKEN_INDEX = 7;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			throw new ServletException("Token inexistente ou mal formatado!");
		}

		String token = header.substring(TOKEN_INDEX);

		try {
			Jwts.parser().setSigningKey("minha_senha").parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
		}

		chain.doFilter(request, response);

	}

}
