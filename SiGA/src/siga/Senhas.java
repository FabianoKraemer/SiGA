/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siga;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author aluno
 */
public class Senhas {
    
    private String login;
    private String senha;

    public Senhas(String login, String senha) {
        this.login = login;
        this.senha = senha;        
    }
    
public boolean VerificaSenha(){
    
    File arquivo;
    
    String usuarioSalvo;
    String senhaSalva;
    
    try{
        arquivo = new File("senhas");
        Scanner leitor = new Scanner(arquivo);
        
        while (leitor.hasNextLine()) {
            String linha = leitor.nextLine();
            String[] partes = linha.split(",");
            usuarioSalvo = partes[0];
            senhaSalva = partes[1];
            
            if (usuarioSalvo.equals(this.login)){
                if(senhaSalva.equals(this.senha)){
                    leitor.close();
                    return true;
                }
            } 
            
        }
        
    leitor.close();
    }catch(Exception e){}
    return false;
}

public void CriarUsuario(){
       
    File arquivo;

    try{
        arquivo = new File("senhas");
        
        FileWriter fwArquivo = null;

        if (arquivo.exists() == true) {
            fwArquivo = new FileWriter(arquivo, true);

        }else{ // se n~ao existir, ent~ao cria o arquivo
            fwArquivo = new FileWriter(arquivo);
            System.out.println("Erro ao abrir arquivo das senhas"); // remover com projeto finalizado
            return;
        }

        String gravar = this.login + "," + this.senha;
        fwArquivo.write(gravar);
        BufferedWriter bw = new BufferedWriter(fwArquivo);
        bw.write("teste");
        // fechando arquivo
        bw.close();
        fwArquivo.close();
        }catch(Exception e){}
      
}

}
