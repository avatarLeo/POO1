/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.av1;

/**
 *
 * @author SILAS
 */
public class Pessoa {
    private String nome;
    private String cpf;
    private String endereco;
    private String nascimento;

    public Pessoa(String nome, String cpf, String endereco, String nascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.nascimento = nascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    
    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNascimento() {
        return nascimento;
    }


    @Override
    public String toString() {
        String res = nome + "\n";
        res += cpf + "\n";
        res += endereco + "\n";
        res += nascimento + "\n";
        return res;
        
    }
    
    
}
