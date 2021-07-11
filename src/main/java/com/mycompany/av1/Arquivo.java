/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.av1;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
/**
 *
 * @author leo
 */
public class Arquivo {
    // em caso de erro verifique o caminho do diretório
    private static String nomePc = System.getProperty("user.name");
    private static String DIR = "/home/" + nomePc +"/Documentos/banco"; 
    
    public void criarDiretorio(){
        File dir = new File(DIR + "/extrato");
        if(dir.exists()){
            
        }else{
            dir.mkdirs();
        }
    }
    
    public boolean criarArquivo(String nome){
        boolean res = false;
        try{
            
            File dir = new File((DIR + "/extrato"));
            File arquivo = new File(dir, nome);

            if(arquivo.exists())
                res = true;
            else
                res = arquivo.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            } 
        return res;
    }
    
    public String[] listaDeArquivos(){
        File dir = new File(DIR);
        String[] nomes = dir.list();
        ArrayList<String> lista = new ArrayList();
        String[] auxiliar = new String[nomes.length - 1];
        
        for(int i = 0; i < nomes.length; i++){
            lista.add(nomes[i]); 
        }
        int in = lista.indexOf("extrato");
        lista.remove(in);
        int i = 0;
        for(String nome: lista){
               auxiliar[i] = nome;
           i++;
        }
        
        return auxiliar;
        
    }
    
    public long numeroDeArquivos(){
        File arq = new File(DIR);
        String[] ar = arq.list();
        return ar.length;
    }
    
    
    public boolean renoarArquivo(String nome, String novoNome){
        File dir = new File(DIR);
        File arq = new File(dir, nome);
        File arq2 = new File(dir, novoNome);
        boolean res = arq.renameTo(arq2);
        return res;
    }
    
    public boolean apagarArquivo(String nome){
        File dir = new File(DIR); 
        File arq = new File(dir, nome);
        boolean res = arq.delete();
        return res;
    }
    
    public void salvar(Cliente c){
        File dir = new File(DIR);
        File arq = new File (dir, c.getNome());
        
        try{
            arq.createNewFile();
            
            FileWriter escrever = new FileWriter(arq);
            
            PrintWriter escreve = new PrintWriter(escrever);
            escreve.println(c);
            
            escreve.flush();
            escreve.close();
        } catch(IOException e){
            e.printStackTrace();
        
        }
        
    }
    
       public void salvarExtrato(String ex, String n){
        File dir = new File(DIR + "/extrato");
        File arq = new File (dir, n);
        
        try{
            if(!arq.exists())
                arq.createNewFile();
            
            FileWriter escrever = new FileWriter(arq, true);
            
            PrintWriter escreve = new PrintWriter(escrever);
            escreve.println(ex);
            
            escreve.flush();
            escreve.close();
        } catch(IOException e){
            e.printStackTrace();
        
        }
       }
       
       public String getExtrato(String n){
        File dir = new File(DIR + "/extrato");
        File arq = new File (dir, n);
        String dados = "";
        try{
            if(arq.exists()){
                FileReader ler = new FileReader(arq);
                BufferedReader buffer = new BufferedReader(ler);
                String linha = "";
                
                
                while((linha = buffer.readLine()) != null){
                    dados += linha + "\n";
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        
        }
        return dados;
       }
       
       
       
    public Cliente encontrarArquivo(String nome){
        File dir = new File(DIR);
        File arq = new File(dir, nome);
        String[] dados = new String[8];
        boolean existe = arq.exists();
        Cliente c;
        try{
            if(existe){
                FileReader ler = new FileReader(arq);
                BufferedReader bufer = new BufferedReader(ler);

                String linha = "";
                byte ind = 0;

                while( (linha = bufer.readLine()) != null){
                   dados[ind] = linha;
                   ind++;
                }
                ler.close();
                bufer.close();
                
                
                
            }else
                c = null;
        } catch( IOException e){
        e.printStackTrace();
        }
        if(existe){
            String nom = dados[0];
            String cpf = dados[1];
            String endereco = dados[2];
            String nascimento = dados[3];
            double salario = Double.parseDouble(dados[4]);
            double saldo = Double.parseDouble(dados[5]);
            String senha = dados[6];
            int conta = Integer.parseInt(dados[7]);

            c = new Cliente(nom, cpf, endereco, nascimento, salario, senha, conta);
            c.setSaldo(saldo);
        } else
            c = null;
        
        return c;
    }
    
    public int arquivoDeControle(){
        File dir = new File(DIR + "/extrato");
        File arq = new File(dir, "numConta");
        int num = 0;
        try{
        if(!arq.exists()){
            arq.createNewFile();
            FileWriter esc = new FileWriter(arq);
            PrintWriter prin = new PrintWriter(esc);
            prin.print("1");
            
            prin.flush();
            prin.close();
        }else{
            FileReader ler = new FileReader(arq);
            BufferedReader buffer = new BufferedReader(ler);
            
            
       
            String linha;
            while((linha = buffer.readLine()) != null){
                num = Integer.parseInt(linha);
                num += 1;
            }
            
            ler.close();
            buffer.close();
            
            FileWriter esc = new FileWriter(arq);
            PrintWriter prin = new PrintWriter(esc);
            prin.println(num);
            
            prin.flush();
            prin.close();
          
        }
            
        }catch (IOException e){
            e.printStackTrace();
        }
        
        return num;
    }
    
    
}
