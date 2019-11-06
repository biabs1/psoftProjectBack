package psoftProjectBack.psoftProjectBack.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	private String nome;
	private String sobrenome;
	@Id
	private String email;
	private String numCartaoCredito;
	private String senha;
	
	public String getEmail() {
		return email;
	}

	public Object getSenha() {
		return senha;
	}

}
