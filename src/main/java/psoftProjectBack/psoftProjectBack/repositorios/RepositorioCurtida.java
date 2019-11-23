package psoftProjectBack.psoftProjectBack.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import psoftProjectBack.psoftProjectBack.entidades.Curtida;

public interface RepositorioCurtida<T, Id extends Serializable> extends JpaRepository<Curtida, Long> {

}
