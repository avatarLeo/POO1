/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.av1;
import java.util.Scanner;
/**
 *
 * @author leo
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InterfaceUsuario user = new InterfaceUsuario();
        Arquivo dados = new Arquivo();
        dados.criarDiretorio();
        
        byte op = -1;
        while (op != 0){
            Scanner lerOp = new Scanner(System.in);
            user.opções();
            op = lerOp.nextByte();
            
            switch(op){
                case 1: user.cadastrar(); break;
                case 2: user.saque(); break;
                case 3: user.deposito(); break;
                case 4: user.transferencia(); break;
                case 5: user.extrato(); break;
                case 6: user.pesquisarCliente(); break;
                case 7: user.listarClientes(); break;
            }
        }
    }
    
   
    
}
