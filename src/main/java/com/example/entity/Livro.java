package com.example.entity;
import java.util.List; 
import java.util.ArrayList;

public class Livro {
    private int id; 
    private String titulo;
    private String autor;
    private String editora;
    private int ano;
    private boolean disponivel;
    private List<Usuario> listaReservas; //lista de usuários que reservaram livros
    
    public Livro(int id, String titulo, String autor, String editora, int ano, boolean disponivel) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano = ano;
        this.disponivel = disponivel;
        this.listaReservas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void reservarLivro(Usuario usuario) { 
        listaReservas.add(usuario);
    }

    public List<Usuario> getReservas() {
        return listaReservas;
    }

    public void limparReservas() {
        listaReservas.clear();
    }
}
