package br.edu.ifrn.biblioteca.repository;

import br.edu.ifrn.biblioteca.model.Emprestimo;
import br.edu.ifrn.biblioteca.model.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    // Listar empréstimos ativos de usuário específico
    @Query("SELECT e FROM Emprestimo e " +
           "JOIN FETCH e.itensEmprestimo i " +
           " JOIN FETCH i.livro l " +
           "WHERE e.usuario.id = :usuarioId " +
           "AND e.status = :status")

    List<Emprestimo> findEmprestimosAtivosPorUsuario
            (@Param("usuarioId") Long usuarioId,
             @Param("status") StatusEmprestimo status);

    //Listar emprestimos atrasados
    @Query("SELECT e FROM Emprestimo e " +
           "JOIN FETCH e.usuario u " +
           "JOIN FETCH e.itensEmprestimo i " +
           "JOIN FETCH i.livro l " +
           "WHERE e.dataDevolucaoPrevista < :dataAtual " +
           "AND e.status = :status")

    List<Emprestimo> findEmprestimosAtrasados(
            @Param("dataAtual") LocalDate dataAtual,
            @Param("status")  StatusEmprestimo status);
}
