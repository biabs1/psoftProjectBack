package psoftProjectBack.psoftProjectBack.entidades;

import javax.persistence.OneToOne;

public class Like {
	
	private Usuario quemDeuLike;
	@OneToOne
	private Campanha campanha; 

}
