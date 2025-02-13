package com.example.entity;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String preferenciaNotificacao;

    public Usuario(int id, String nome, String cpf, String email, String preferenciaNotificacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.preferenciaNotificacao = preferenciaNotificacao;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPreferenciaNotificacao() {
        return preferenciaNotificacao;
    }

    public void setPreferenciaNotificacao(String preferenciaNotificacao) {
        this.preferenciaNotificacao = preferenciaNotificacao;
    }
}
