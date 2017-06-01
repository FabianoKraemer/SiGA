/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siga;

/**
 *
 * @author aluno
 */
public class Evento {
    
    private String sequencia;
    private String dataHora;
    private String nomeAreaOrigem;
    private String nomeAreaDestino;
    private String usuario;
    private String matricula;
    private String nome;

    public Evento(String sequencia, String dataHora, String nomeAreaOrigem, String nomeAreaDestino, String usuario, String matricula, String nome) {
        this.sequencia = sequencia;
        this.dataHora = dataHora;
        this.nomeAreaOrigem = nomeAreaOrigem;
        this.nomeAreaDestino = nomeAreaDestino;
        this.usuario = usuario;
        this.matricula = matricula;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Evento{" + "sequencia=" + sequencia + ", dataHora=" + dataHora + ", nomeAreaOrigem=" + nomeAreaOrigem + ", nomeAreaDestino=" + nomeAreaDestino + ", usuario=" + usuario + ", matricula=" + matricula + ", nome=" + nome + '}';
    }
    
    
        
    
    
    
}
