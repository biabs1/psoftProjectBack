package psoftProjectBack.psoftProjectBack.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import psoftProjectBack.psoftProjectBack.entidades.Comentario;
import psoftProjectBack.psoftProjectBack.entidades.Usuario;

public interface RepositorioComentario<T, Id extends Serializable> extends JpaRepository<Comentario, Long> {

}
