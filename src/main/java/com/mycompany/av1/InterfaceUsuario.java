/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.av1;
import com.mycompany.av1.janelas.Inicio;
import com.mycompany.av1.janelas.TelaInicial;
import java.util.Scanner;
import java.io.IOException;
/**
 *
 * @author leo
 */
public class InterfaceUsuario {
    
    public void opções(){
        System.out.println("|====================================================|");
        System.out.println("|-----------------SISTEMA BANCÁRIO-------------------|");
        System.out.println("|====================================================|");
        System.out.println("| Cadastrar correntista [1]                          |");
        System.out.println("| Saque [2]                                          |");
        System.out.println("| Depósito [3]                                       |");
        System.out.println("| Transferência [4]                                  |");
        System.out.println("| Extrato [5]                                        |");
        System.out.println("| Pesquisar Cliente [6]                              |");
        System.out.println("| Lista de Clientes [7]                              |");
        System.out.println("|====================================================|");
        System.out.println("| Escolha uma opção ou [0] para sair                 |");
        System.out.println("|====================================================|");
        
        
        
        //new Editar().setVisible(true);
    }
    
    public void cadastrar(){
        String nome;
        String cpf;
        String endereco;
        String nascimento;
        double salario;    
        int conta;
        String senha;
        Scanner ler = new Scanner(System.in);
        Arquivo a = new Arquivo();
        
        
        System.out.println("Informe os dados do cliente");
        System.out.println("============================");
        System.out.println("Nome: ");
        nome = ler.nextLine();
        
        System.out.println("CPF: ");
        cpf = ler.nextLine();
        
        System.out.println("Endereço: ");
        ler.nextLine();
        
        endereco = ler.nextLine();
        
        System.out.println("Nascimento: ");
        nascimento = ler.nextLine();
        
        System.out.println("Salário: ");
        salario = ler.nextDouble();
        
        ler.nextLine();
        System.out.println("Senha: ");
        senha = ler.nextLine();
       
       conta = a.arquivoDeControle();
        
        Cliente c = new Cliente(nome, cpf, endereco, nascimento, salario, senha, conta);
        
        a.salvar(c);
        
        System.out.println("Cliente cadastrado com sucesso!");
        System.out.println("Nome: " + c.getNome());
        System.out.println("Número da conta: " + c.getConta());
        System.out.println("Pressione enter para continuar\n");
        ler.nextLine();
    }
    
    public void pesquisarCliente(){
        Arquivo dado = new Arquivo();
        
        Cliente c;
        
        Scanner ler = new Scanner(System.in);
        String nome;
        
        
        System.out.println("Informe o nome do cliente: ");
        nome = ler.nextLine();
        
        
        c = dado.encontrarArquivo(nome);
        if(c != null){
            System.out.println("Dados do(a) cliente(a) " + c.getNome());
            System.out.println("CPF: " + c.getCpf());
            System.out.println("Endereço: " + c.getEndereco());
            System.out.println("Nascimento: " + c.getNascimento());
            System.out.println("Conta: " + c.getConta());
            System.out.println("Saldo: " + c.getSaldo());
            System.out.println("Salário: " + c.getSalario());
        }else
            System.out.println("ERROR! Cliente " + nome + " não encontrado\n");
    }
    
    public void listarClientes(){
        Arquivo dados = new Arquivo();
        String[] res = dados.listaDeArquivos();
        Scanner ler = new Scanner(System.in);
        Cliente c;
        
        int op = 0;
        if(res.length > 1){
            for(int i = 0; i < res.length; i++){
                int ind = i + 1;
                System.out.println(res[i] + "[" + ind +"]");
            }    
                System.out.println("================================");
                System.out.println("Escolha um número referente ao cliente desejado:");
                op = ler.nextInt();
                
            if(op > 0 && op <= res.length)
                c = dados.encontrarArquivo(res[op - 1]);
            else
                c = null;
            
            if(c != null){
                System.out.println("Dados do(a) cliente(a) " + c.getNome());
                System.out.println("CPF: " + c.getCpf());
                System.out.println("Endereço: " + c.getEndereco());
                System.out.println("Nascimento: " + c.getNascimento());
                System.out.println("Conta: " + c.getConta());
                System.out.println("Saldo: " + c.getSaldo());
                System.out.println("Salário: " + c.getSalario());
        }else
            System.out.println("ERROR! Cliente  não encontrado\n");
         
        }else
            System.out.println("Não ha clientes cadastrados!\n");
   }
    
     public void deposito(){
         Scanner ler = new Scanner(System.in);
         Arquivo arq = new Arquivo();
         OperacaoBancaria banco = new OperacaoBancaria();
         String nome;
         byte op;
         double valor;
         
         System.out.println("Informe o nome do cliente");
         nome = ler.nextLine();
         System.out.println("Informe o valor do depósito");
         valor = ler.nextDouble();
         
         Cliente c = arq.encontrarArquivo(nome);
         if(c != null){
             System.out.println("Por favor confira os dados do cliente");
             System.out.println("==========================================");
             System.out.println("Nome: " + c.getNome());
             System.out.println("CPF: " + c.getCpf());
             System.out.println("Número da conta: " + c.getConta());
             System.out.println("==========================================");
             System.out.println("Digite [1] para proseguir e [0] para cancelar");
             op = ler.nextByte();
             if(op == 1){
                if(banco.deposito(valor, c, (byte)1))
                     System.out.println("Depósito feito com sucesso\n");
                else
                     System.out.println("ERRO! não conseguimos realizar o depósito\n");
             }
         }else
             System.out.println("Cliente não encontrado\n");
     } 
     
     
         public void saque(){
         Scanner ler = new Scanner(System.in);
         Arquivo arq = new Arquivo();
         OperacaoBancaria banco = new OperacaoBancaria();
         String nome;
         String senha;
         byte op;
         double valor;
         
         System.out.println("Informe o nome do cliente: ");
         nome = ler.nextLine();
         System.out.println("Informe a senha: ");
         senha = ler.nextLine();
         if(banco.validaCliente(nome, senha)){
         
            System.out.println("Informe o valor do saque: ");
            valor = ler.nextDouble();

            Cliente c = arq.encontrarArquivo(nome);
            if(c != null){
                System.out.println("Por favor confira os dados do cliente");
                System.out.println("==========================================");
                System.out.println("Nome: " + c.getNome());
                System.out.println("CPF: " + c.getCpf());
                System.out.println("Número da conta: " + c.getConta());
                System.out.println("Saldo: " + c.getSaldo());
                System.out.println("==========================================");
                System.out.println("Digite [1] para proseguir e [0] para cancelar");
                op = ler.nextByte();
                if(op == 1){
                   if(banco.saque(valor, c, (byte)1))
                        System.out.println("Saque feito com sucesso\n");
                   else
                        System.out.println("ERRO! não conseguimos realizar o saque\n");
                }
            }else
                System.out.println("Cliente não encontrado\n");
     }else
         System.out.println("SENHA OU CLIENTE INVÁLIDOS\n"); 
    }   
    
     
     
     public void extrato(){
         Scanner ler = new Scanner(System.in);
         Arquivo dado = new Arquivo();
         OperacaoBancaria banco = new OperacaoBancaria();
         String nome;
         String senha;
         String extrato;
         byte op = 1;
         while(op != 0){
            System.out.println("Por favor informe o nome e a senha do cliente");
            System.out.println("==============================================");
            System.out.println("Nome: ");
            nome = ler.nextLine();
            System.out.println("Senha: ");
            senha = ler.nextLine();
            if(!nome.isBlank()&& !senha.isBlank()){
                Cliente c = dado.encontrarArquivo(nome);
                if(banco.extratoBnacario(senha, c) ){

                    extrato = dado.getExtrato(c.getNome());
                    System.out.println(extrato);
                    System.out.println("===========================================");
                    System.out.println("Digite 0 para sair ou 1 para uma nova consulta");
                    op = ler.nextByte();
                    if(op == 1)
                        ler.nextLine();
                }else{
                     System.out.println("Cliente ou senha incorretos digite 0 para sair ou 1 para continuar");
                     op = ler.nextByte();
                     if(op == 1)
                        ler.nextLine();
                }
            }else{
                System.out.println("Cliente ou senha incorretos digite 0 para sair ou 1 para continuar");
                     op = ler.nextByte();
                     if(op == 1)
                        ler.nextLine();
            }
                
         }
     }
     
     public void transferencia(){
         Scanner ler = new Scanner(System.in);
         OperacaoBancaria banco = new OperacaoBancaria();
         String nome;
         String senha;
         byte op = 1;
         
         while(op != 0){
            Arquivo cliente = new Arquivo();
            double valor;
            System.out.println("Por favor informe os seu dados");
            System.out.println("Nome: ");
            nome = ler.nextLine();
            System.out.println("Senha: ");
            senha = ler.nextLine();
            if(!nome.isBlank() && !senha.isBlank()){
                if(banco.validaCliente(nome, senha)){
                    Cliente c1 = cliente.encontrarArquivo(nome);
                    System.out.println("Dados do cliente para a transferência");
                    System.out.println("Nome: ");
                    String nomeParaTransferir = ler.nextLine();
                    
                    Cliente c2 = cliente.encontrarArquivo(nomeParaTransferir);
                    
                    
                    if(c2 != null){
                        System.out.println("Número da conta do " + c2.getNome());
                        int numeroDaConta = ler.nextInt();
                        
                        System.out.println("Informe o valor da transferência");
                        valor = ler.nextDouble();
                        
                        if(banco.transferencia(c1, c2, nomeParaTransferir, numeroDaConta, valor)){
                            System.out.println("Transferência realizada com sucesso!");
                            System.out.println("Digite 0 para sair ou 1 para tentar novamente");
                            op = ler.nextByte();
                            ler.nextLine();
                        }else{
                            System.out.println("Ocorreu um erro verifique os dados da sua transferência");
                            System.out.println("Número da conta e o valor");
                            System.out.println("Digite 0 para sair ou 1 para tentar novamente");
                            op = ler.nextByte();
                            ler.nextLine();
                        }
                        }else{
                            System.out.println("Nome " + nomeParaTransferir + " não encontrado");
                            System.out.println("Digite 0 para sair ou 1 para tentar novamente");
                            op = ler.nextByte();
                            ler.nextLine();
                    }
                }else{
                    System.out.println("Cliente ou senhas inválidos");
                    System.out.println("Digite 0 para sair ou 1 para tentar novamente");
                    op = ler.nextByte();
                    ler.nextLine();
                }
         }else{
            System.out.println("Digite um cliente e uma senha");
            System.out.println("Digite 0 para sair ou 1 para tentar novamente");
            op = ler.nextByte();
            ler.nextLine();
            }
     }
     }  
     
     public void interfaceGrafica(){
         new TelaInicial().setVisible(true);
     }
    
  
}
