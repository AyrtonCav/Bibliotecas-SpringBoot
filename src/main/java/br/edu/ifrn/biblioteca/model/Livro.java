package br.edu.ifrn.biblioteca.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,  nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String editora;

    @Column(nullable = false)
    private Integer anoPublicacao;

    @Column(nullable = false)
    private Integer numeroPaginas;

    @Column(nullable = false)
    private Integer quantidadeTotal;

    @Column(nullable = false)
    private Integer quantidadeDisponivel;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany
    @JoinTable(
            name = "LIVRO_AUTOR",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

     @OneToMany(mappedBy = "livro")
     private List<ItemEmprestimo> itensEmprestimo = new ArrayList<>();

    public Livro() {}

    public Livro(String isbn, String titulo, String editora, Integer anoPublicacao,
                 Integer numeroPaginas, Integer quantidadeTotal, Integer quantidadeDisponivel,
                 Categoria categoria) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.numeroPaginas = numeroPaginas;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.categoria = categoria;
    }

    // --- GETTERS E SETTERS ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }

    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public Integer getNumeroPaginas() { return numeroPaginas; }
    public void setNumeroPaginas(Integer numeroPaginas) { this.numeroPaginas = numeroPaginas; }

    public Integer getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(Integer quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }

    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public List<Autor> getAutores() { return autores; }
    public void setAutores(List<Autor> autores) { this.autores = autores; }

    public List<ItemEmprestimo> getItensEmprestimo() { return itensEmprestimo; }
    public void setItensEmprestimo(List<ItemEmprestimo> itensEmprestimo) { this.itensEmprestimo = itensEmprestimo; }

    // Métodos auxiliares
    public void addAutor(Autor autor) {
        this.autores.add(autor);
        autor.getLivros().add(this); // Assumindo que Autor também terá a lista de livros
    }

    public void removeAutor(Autor autor) {
        this.autores.remove(autor);
        autor.getLivros().remove(this);
    }
}