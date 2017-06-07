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
    public enum Turno {MANHA, TARDE, NOITE }

    private String nome;
    private String matricula;
    private String turma;
    private String codigo;
    private String validadeAcesso;
    private String permissao;
    private String situacao;
    private String turno;
    private ArrayList<Evento> eventos;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValidadeAcesso() {
        return validadeAcesso;
    }

    public void setValidadeAcesso(String validadeAcesso) {
        this.validadeAcesso = validadeAcesso;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

}
