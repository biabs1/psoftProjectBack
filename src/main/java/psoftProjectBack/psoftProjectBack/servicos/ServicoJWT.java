package psoftProjectBack.psoftProjectBack.servicos;

import org.springframework.stereotype.Service;

@Service
public class ServicoJWT {
	private ServicoUsuario servicoUsuario;
	
	public ServicoJWT(ServicoUsuario servicoUsuario) {
		super();
		this.servicoUsuario = servicoUsuario;
	}

}
