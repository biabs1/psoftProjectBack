package psoftProjectBack.psoftProjectBack.entidades;

import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
	
	private Usuario quemComentou;
	private String texto;
	@OneToOne
	@JsonIgnore
	private Campanha campanha; 
	
}
