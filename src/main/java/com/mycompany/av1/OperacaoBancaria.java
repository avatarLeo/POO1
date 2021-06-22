/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.av1;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author leo
 */
public class OperacaoBancaria {
    private static String registro;
    
    public static long numeroDaConta(){
        Arquivo num = new Arquivo();
        long nu = num.numeroDeArquivos();
        return nu += 1;
    }
    
    public boolean deposito(double valor, Cliente c, byte in){
        Date data = new Date();
        SimpleDateFormat da = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        Arquivo salvar = new Arquivo();
        registro = da.format(data);
        boolean res;
        
        if(valor > 0){
            c.setSaldo(valor + c.getSaldo());
            registro += " R$ " + valor;
            switch(in){
                case 1: registro += " depositado"; break;
                case 2: registro += " transferência recebida";break;
            }
            
            salvar.salvarExtrato(registro, c.getNome());
            
            salvar.salvar(c);
            res = true;
        }else
            res = false;
        
        return res;
    }
    
        
    public boolean saque(double valor, Cliente c, byte in){
        Date data = new Date();
        SimpleDateFormat da = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        Arquivo salvar = new Arquivo();
        registro = da.format(data);
        boolean res;
        
        if(valor <= c.getSaldo()){
            c.setSaldo(c.getSaldo() - valor);
            registro += " R$ " + valor;
             switch(in){
                case 1: registro += " sacado"; break;
                case 2: registro += " transferência feita para outra conta"; break;
             }
            salvar.salvarExtrato(registro, c.getNome());
            salvar.salvar(c);
            res = true;
        }else
            res = false;
        
        return res;
    }
    
    
    public boolean extratoBnacario(String sen, Cliente c){
        if(c != null){
            if(sen.equals(c.getSenha()))
                return true;
            else
                return false;
        }else
            return false;
    }
    
    public boolean validaCliente(String no, String sen){
        Arquivo cliente = new Arquivo();
        Cliente c = cliente.encontrarArquivo(no);
        if(c != null){
            if(no.equals(c.getNome()) && sen.equals(c.getSenha())){
                return true;
            }else
                return false;
        }else
            return false;
    }
    
    public boolean transferencia(Cliente c1, Cliente c2, String no, int conta, double valor){
        if((no.equals(c2.getNome()) && (conta == c2.getConta()))){
            if((saque(valor, c1, (byte)2))){
                return deposito(valor, c2, (byte)2);
            }return false;
        }else
            return false;
    }
}


