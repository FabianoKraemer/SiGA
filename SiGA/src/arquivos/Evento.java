/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import arquivos.Aluno;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class Evento {

    private String sequencia;
    private Calendar dataHora;
    private String nomeAreaOrigem;
    private String nomeAreaDestino;
    private String usuario;
    private String matricula;
    private String nome;
    private Aluno aluno;

    private Boolean entrada;
    private Boolean saidaAdiantada;
    private Boolean entradaAtrasada;
    private int minutosAdiantados;
    private int minutosAtrasados;

    public Evento(String sequencia, String dataHoraString, String nomeAreaOrigem, String nomeAreaDestino, String usuario, String matricula, String nome) {
        this.sequencia = sequencia;
        this.dataHora = Calendar.getInstance();
        
        try {
            //2017-04-03 12:09:02.0000000
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            this.dataHora.setTime(f.parse(dataHoraString));
                        
        } catch (ParseException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.nomeAreaOrigem = nomeAreaOrigem;
        this.nomeAreaDestino = nomeAreaDestino;
        this.usuario = usuario;
        this.matricula = matricula;
        this.nome = nome;

        if ((this.nomeAreaOrigem + this.nomeAreaDestino).equals("ExternaInterna")) {
            this.entrada = true;
        } else {
            this.entrada = false;
        }
    }

    public Boolean getSaidaAdiantada() {
        if (this.aluno != null) {
            String turno = this.aluno.getTurno();
            switch (turno) {
                case "M":
                   // this.dataHora.add()
                    break;
            }
        }
        return saidaAdiantada;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public String getNomeAreaOrigem() {
        return nomeAreaOrigem;
    }

    public void setNomeAreaOrigem(String nomeAreaOrigem) {
        this.nomeAreaOrigem = nomeAreaOrigem;
    }

    public String getNomeAreaDestino() {
        return nomeAreaDestino;
    }

    public void setNomeAreaDestino(String nomeAreaDestino) {
        this.nomeAreaDestino = nomeAreaDestino;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Boolean getEntrada() {
        return entrada;
    }

    public void setEntrada(Boolean entrada) {
        this.entrada = entrada;
    }

    public Boolean getEntradaAtrasada() {
        return entradaAtrasada;
    }

    public void setEntradaAtrasada(Boolean entradaAtrasada) {
        this.entradaAtrasada = entradaAtrasada;
    }

    public int getMinutosAdiantados() {
        return minutosAdiantados;
    }

    public void setMinutosAdiantados(int minutosAdiantados) {
        this.minutosAdiantados = minutosAdiantados;
    }

    public int getMinutosAtrasados() {
        return minutosAtrasados;
    }

    public void setMinutosAtrasados(int minutosAtrasados) {
        this.minutosAtrasados = minutosAtrasados;
    }

    

    @Override
    public String toString() {
        return "Evento{" + "sequencia=" + sequencia + ", dataHora=" + dataHora.toString() + ", nomeAreaOrigem=" + nomeAreaOrigem + ", nomeAreaDestino=" + nomeAreaDestino + ", usuario=" + usuario + ", matricula=" + matricula + ", nome=" + nome + '}';
    }

}
