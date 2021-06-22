/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.av1;

/**
 *
 * @author leo
 */
public class Cliente extends Pessoa{
    private double saldo;
    private double salario;
    private int conta;
    private String senha;

    
    Cliente(String n, long cpf, String end, String nasc,
            double sa, String sen, int con){
        
        super(n, cpf, end, nasc);
        
        salario = sa;
        conta = con;
        senha = sen;
}

    public double getSaldo() {
        return saldo;
    }

    public double getSalario() {
        return salario;
    }

    public int getConta() {
        return conta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setConta(int conta) {
        this.conta = conta;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    public String toString(){
        String res = super.toString();
   
        res += salario + "\n";
        res += saldo + "\n";
        res += senha + "\n";
        res += conta;
        return res;
    }

   
    
    
}


