package psoftProjectBack.psoftProjectBack.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@RestController
public class ControladorUsuario {
	
	private ServicoUsuario servicoUsuario;
	private ServicoJWT servicoJWT;
	
	public ControladorUsuario(ServicoUsuario servicoUsuario, ServicoJWT servicoJWT) {
		this.servicoUsuario = servicoUsuario;
		this.servicoJWT = servicoJWT;
	}
	
	@PostMapping("/usuarios")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
    	return new ResponseEntity<Usuario>(servicoUsuario.adicionarUsuario(usuario),
    			HttpStatus.OK);
    }
	
	
	

}
