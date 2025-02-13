package com.example.entity;
import java.time.LocalDate; //o tipo da data

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private Usuario usuario;
    private Livro livro;
    
    public Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucao, Usuario usuario, Livro livro) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.usuario = usuario;
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }
    
    public void setLivro(Livro livro) { //verificação se o atributo 'disponivel' do objeto livro
        if (livro.isDisponivel()) {
            this.livro = livro;
            livro.setDisponivel(false);
        } else {
            throw new IllegalStateException("Livro não está disponível para empréstimo."); //lança uma exceção, interrompende o fluxo do programa, porque o estado atual é inválido (já que o livro não está disponível)
        }
    }
} 
