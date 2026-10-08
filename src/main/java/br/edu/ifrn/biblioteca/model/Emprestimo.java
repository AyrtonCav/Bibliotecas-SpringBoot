package br.edu.ifrn.biblioteca.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataEmprestimo;

    @Column(nullable = false)
    private LocalDate dataDevolucaoPrevista;

    @Column(nullable = false)
    private LocalDate dataDevolucaoEfetiva;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @Column(nullable = false)
    private BigDecimal valorMulta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEmprestimo> itensEmprestimo = new ArrayList<>();

    // --- CONSTRUCTS ---

    public Emprestimo() {}

    public Emprestimo(Usuario usuario, LocalDate dataDevolucaoPrevista) {
        this.usuario = usuario;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataEmprestimo = LocalDate.now();
        this.status = StatusEmprestimo.ATIVO;
        this.valorMulta = BigDecimal.ZERO;
        this.itensEmprestimo = new ArrayList<>();
    }

    // --- GETTERS E SETTERS ---


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDate getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }

    public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) {
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public BigDecimal getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(BigDecimal valorMulta) {
        this.valorMulta = valorMulta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ItemEmprestimo> getItensEmprestimo() {
        return itensEmprestimo;
    }

    public void setItensEmprestimo(List<ItemEmprestimo> itensEmprestimo) {
        this.itensEmprestimo = itensEmprestimo;
    }

    public void addItem(ItemEmprestimo item) {
        this.itensEmprestimo.add(item);
        item.setEmprestimo(this);
    }
}