/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

/**
 *
 * @author aluno
 */
public class Alerta {

    private int tipoAlerta;
    private String descricaoAlerta;
    private String nomeAluno;
    private int quantidadeMinimaDeDiasDeFalta;
    private boolean consecutivo;
    private String diaEspecifico;
    private String turma;
    private int minutosAtraso;
    private int minutosAdianto;
    private int quantidadeMinimaDeDiasDeAtraso;
    private int quantidadeMinimaDeDiasDeAdianto;

    public String getDescricaoAlerta() {
        return descricaoAlerta;
    }

    public void setDescricaoAlerta(String descricaoAlerta) {
        this.descricaoAlerta = descricaoAlerta;
    }

    public int getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(int tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public int getQuantidadeMinimaDeDiasDeAtraso() {
        return quantidadeMinimaDeDiasDeAtraso;
    }

    public void setQuantidadeMinimaDeDiasDeAtraso(int quantidadeMinimaDeDiasDeAtraso) {
        this.quantidadeMinimaDeDiasDeAtraso = quantidadeMinimaDeDiasDeAtraso;
    }

    public int getQuantidadeMinimaDeDiasDeAdianto() {
        return quantidadeMinimaDeDiasDeAdianto;
    }

    public void setQuantidadeMinimaDeDiasDeAdianto(int quantidadeMinimaDeDiasDeAdianto) {
        this.quantidadeMinimaDeDiasDeAdianto = quantidadeMinimaDeDiasDeAdianto;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getQuantidadeMinimaDeDiasDeFalta() {
        return quantidadeMinimaDeDiasDeFalta;
    }

    public void setQuantidadeMinimaDeDiasDeFalta(int quantidadeMinimaDeDiasDeFalta) {
        this.quantidadeMinimaDeDiasDeFalta = quantidadeMinimaDeDiasDeFalta;
    }

    public boolean isConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(boolean consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getDiaEspecifico() {
        return diaEspecifico;
    }

    public void setDiaEspecifico(String diaEspecifico) {
        this.diaEspecifico = diaEspecifico;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public int getMinutosAtraso() {
        return minutosAtraso;
    }

    public void setMinutosAtraso(int minutosAtraso) {
        this.minutosAtraso = minutosAtraso;
    }

    public int getMinutosAdianto() {
        return minutosAdianto;
    }

    public void setMinutosAdianto(int minutosAdianto) {
        this.minutosAdianto = minutosAdianto;
    }

}
