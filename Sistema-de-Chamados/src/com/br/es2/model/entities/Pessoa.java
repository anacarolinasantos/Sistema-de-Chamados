package com.br.es2.model.entities;

import java.io.Serializable;

/**
 *
 * @author Richard
 */
public class Pessoa implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String nome;
    private long telefone;

    public Pessoa(String nome, long telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    
    
    
}
