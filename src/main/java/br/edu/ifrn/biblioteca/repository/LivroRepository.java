package br.edu.ifrn.biblioteca.repository;

import br.edu.ifrn.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Listar todos os livros disponíveis
    @Query("SELECT l FROM Livro l WHERE l.quantidadeDisponivel > 0 ORDER BY l.titulo ASC")
    List<Livro> findLivrosDisponiveis();

    // Listar livros por categoria
    @Query("SELECT l FROM Livro l JOIN l.categoria c WHERE c.nome = :nomeCategoria")
    List<Livro> findLivrosCategoria(@Param("nomeCategoria") String nomeCategoria);

    // Listar livros por nome do autor
    @Query("SELECT l FROM Livro l JOIN l.autores a WHERE a.nome = :nomeAutor ORDER BY l.anoPublicacao DESC")
    List<Livro> findLivrosAutores(@Param("nomeAutor") String nomeAutor);
}