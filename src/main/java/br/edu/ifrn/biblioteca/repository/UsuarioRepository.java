package br.edu.ifrn.biblioteca.repository;

import br.edu.ifrn.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuários por nome parcial
    //@Query("SELECT u FROM Usuario u WHERE LOWER(u.nome) LIKE LOWER(CONCAT('%', :nomeUsuario, '%'))")
    //List<Usuario> findByNomeUsuario(@Param("nomeUsuario") String nomeUsuario);

    List<Usuario> findByNomeContainingIgnoreCase(String nome);
}
