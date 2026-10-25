package br.edu.ifrn.biblioteca.repository;

import br.edu.ifrn.biblioteca.dto.CategoriaContagemDTO;
import br.edu.ifrn.biblioteca.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Contar quantidade de livros por categoria
    @Query("SELECT c.nome AS nome, COUNT(l.id) AS quantidade " +
            "FROM Livro l JOIN l.categoria c " +
            "GROUP BY c.nome " +
            "ORDER BY COUNT(l.id) DESC")
    List<CategoriaContagemDTO> contarLivrosPorCategoria();

}