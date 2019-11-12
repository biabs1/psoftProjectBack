package psoftProjectBack.psoftProjectBack.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import psoftProjectBack.psoftProjectBack.entidades.Usuario;
import psoftProjectBack.psoftProjectBack.servicos.ServicoJWT;
import psoftProjectBack.psoftProjectBack.servicos.ServicoUsuario;

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
	
	@PostMapping("/usuarios")
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
	
}
