package psoftProjectBack.psoftProjectBack.repositorios;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;

public interface RepositorioCampanha<T, ID extends Serializable> extends JpaRepository<Campanha, Long> {

	List<Campanha> findByNomeIgnoreCaseContaining(String nome);
	
	List<Campanha> findAllByOrderByDeadlineAsc();
	
	//List<Campanha> findTop5ByOrderByDeadline();
	
	List<Campanha> findTop5ByOrderByMeta();
	
	List<Campanha> findTop5ByOrderByCurtidas();

}
