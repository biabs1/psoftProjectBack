package psoftProjectBack.psoftProjectBack.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import psoftProjectBack.psoftProjectBack.entidades.Doacao;

public interface RepositorioDoacao<T, Id extends Serializable> extends JpaRepository<Doacao, Long> {

}
