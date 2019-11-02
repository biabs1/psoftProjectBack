package psoftProjectBack.psoftProjectBack.repositorios;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import psoftProjectBack.psoftProjectBack.entidades.Usuario;

public interface RepositorioUsuario<T, Email extends Serializable> extends JpaRepository<Usuario, String>  {

}
