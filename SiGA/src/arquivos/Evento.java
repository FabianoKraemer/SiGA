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

    String sequencia;
    Calendar dataHora;
    String nomeAreaOrigem;
    String nomeAreaDestino;
    String usuario;
    String matricula;
    String nome;
    Aluno aluno;

    Boolean entrada;
    Boolean saidaAdiantada;
    Boolean entradaAtrasada;
    int minutosAdiantados;
    int minutosAtrasados;

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
            String turno = this.aluno.turno;
            switch (turno) {
                case "M":
                   // this.dataHora.add()
                    break;
            }
        }
        return saidaAdiantada;
    }

    public Boolean getEntradaAtrasada() {
        return entradaAtrasada;
    }

    public int getMinutosAdiantados() {
        return minutosAdiantados;
    }

    public int getMinutosAtrasados() {
        return minutosAtrasados;
    }

    @Override
    public String toString() {
        return "Evento{" + "sequencia=" + sequencia + ", dataHora=" + dataHora.toString() + ", nomeAreaOrigem=" + nomeAreaOrigem + ", nomeAreaDestino=" + nomeAreaDestino + ", usuario=" + usuario + ", matricula=" + matricula + ", nome=" + nome + '}';
    }

}
