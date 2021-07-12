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
public class Gambiarra {
    
    public Cliente iniciaCliente(String n, String cpf, String end, String nasc,
            double sa, String sen, int con){
        Cliente c = new Cliente(n,cpf, end, nasc, sa, sen, con);
        
        return c;
    }

}
