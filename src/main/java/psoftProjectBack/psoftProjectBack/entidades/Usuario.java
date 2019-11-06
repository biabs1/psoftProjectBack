package psoftProjectBack.psoftProjectBack.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario {

	private String nome;
	private String sobrenome;
	@Id
	private String email;
	private String numCartaoCredito;
	private String senha;
	
	public Usuario() {
		super();
	}
	
	public Usuario(String nome, String sobrenome, String email,
			String numCartaoCredito, String senha) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.numCartaoCredito = numCartaoCredito;
		this.senha = senha;
	}
	
	public String getEmail() {
		return email;
	}

	public Object getSenha() {
		return senha;
	}

}
