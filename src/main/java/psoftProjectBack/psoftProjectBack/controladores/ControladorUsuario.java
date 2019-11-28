package psoftProjectBack.psoftProjectBack.controladores;

import java.util.Optional;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.annotations.Api;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

@Api(value="Sistema de gerenciamento de usuario", description="Controlador do gerenciamento de usuarios no sistema")
@RestController
public class ControladorUsuario {
	
	private ServicoUsuario servicoUsuario;
	private ServicoJWT servicoJWT;
	
	@Autowired
    private JavaMailSender enviadorEmailJava;
	
	public ControladorUsuario(ServicoUsuario servicoUsuario, ServicoJWT servicoJWT) {
		this.servicoUsuario = servicoUsuario;
		this.servicoJWT = servicoJWT;
	}
	
	@PostMapping("/usuario")
    public ResponseEntity<Usuario> addUsuario(@RequestBody Usuario usuario) {
		String email = usuario.getEmail();
		Optional<Usuario> usuarioEncontrado = servicoUsuario.getUsuario(email);
		
		if (usuarioEncontrado.isPresent()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Email ja cadastrado");
		} else {
			sendEmail(email);
			return new ResponseEntity<Usuario>(servicoUsuario.adicionarUsuario(usuario),
	    			HttpStatus.CREATED);
		}
    	
    }
	
	void sendEmail(String email) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Boas Vindas ao AJuDE");
        msg.setText("Olá, muito bem vindo(a) ao AJuDE!\n<a href=\"https://www.w3schools.com/html/\">Visite-nos</a>");

        enviadorEmailJava.send(msg);

    }
	
	@GetMapping("/usuario")
	public ResponseEntity<Usuario> getUsuario(@RequestHeader("Authorization") String header) throws ServletException {
		String email = servicoJWT.recuperarSujeitoDoToken(header);
		if (!servicoUsuario.getUsuario(email).isPresent()) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Usuario>(servicoUsuario.getUsuario(email).get(),  HttpStatus.OK);	
	}
	
}
