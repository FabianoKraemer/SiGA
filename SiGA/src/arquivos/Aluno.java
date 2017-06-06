/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class Aluno {
    
        String nome;
        String matricula;
        String turma;
        String codigo;
        String validadeAcesso;
        String permissao; 
        String situacao;
        String turno;
        ArrayList<Evento> eventos;
        
    public Aluno(String nome, String matricula, String turma, String codigo, String validadeAcesso, String permissao, String situacao, String turno) {
        this.nome = nome;
        this.matricula = matricula;
        this.turma = turma;
        this.codigo = codigo;
        this.validadeAcesso = validadeAcesso;
        this.permissao = permissao;
        this.situacao = situacao;
        this.turno = turno;
        this.eventos = new ArrayList<Evento>();
    }

    @Override
    public String toString() {
        return "Aluno{" + "nome=" + nome + ", matricula=" + matricula + ", turma=" + turma + ", codigo=" + codigo + ", validadeAcesso=" + validadeAcesso + ", permissao=" + permissao + ", situacao=" + situacao + ", turno=" + turno + '}';
    }

        
}
