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

    private Calendar inicioHoraTurnoManha;
    private Calendar fimHoraTurnoManha;
    private Calendar inicioHoraTurnoTarde;
    private Calendar fimHoraTurnoTarde;
    private Calendar inicioHoraTurnoNoite;
    private Calendar fimHoraTurnoNoite;

    public Calendar getData() {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Calendar cal = getDataHora();

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal;
    }

    public Calendar getInicioHoraTurnoManha() {
        Calendar data = getData();
        data.add(Calendar.HOUR_OF_DAY, 7);
        data.add(Calendar.MINUTE, 30);
        inicioHoraTurnoManha = data;
        return inicioHoraTurnoManha;
    }

    public Calendar getFimHoraTurnoManha() {
        Calendar data = getData();
        data.add(Calendar.HOUR_OF_DAY, 11);
        data.add(Calendar.MINUTE, 30);
        fimHoraTurnoManha = data;
        return fimHoraTurnoManha;
    }

    public Calendar getInicioHoraTurnoTarde() {
        Calendar data = getData();
        data.add(Calendar.HOUR_OF_DAY, 13);
        data.add(Calendar.MINUTE, 30);
        inicioHoraTurnoTarde = data;
        return inicioHoraTurnoTarde;
    }

    public Calendar getFimHoraTurnoTarde() {
        Calendar data = getData();
        data.add(Calendar.HOUR_OF_DAY, 17);
        data.add(Calendar.MINUTE, 30);
        fimHoraTurnoTarde = data;
        return fimHoraTurnoTarde;
    }

    public Calendar getInicioHoraTurnoNoite() {
        Calendar data = getData();
        data.add(Calendar.HOUR_OF_DAY, 18);
        data.add(Calendar.MINUTE, 30);
        inicioHoraTurnoNoite = data;
        return inicioHoraTurnoNoite;
    }

    public Calendar getFimHoraTurnoNoite() {
        Calendar data = getData();
        data.add(Calendar.HOUR_OF_DAY, 22);
        data.add(Calendar.MINUTE, 30);
        fimHoraTurnoManha = data;
        return fimHoraTurnoManha;
    }

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
        this.dataHora = new GregorianCalendar();
        //this.dataHora = Calendar.getInstance();

        try {
            //2017-04-03 12:09:02.0000000
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS");
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

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }

    public Calendar getDataHora() {
        return (Calendar) dataHora.clone();
    }

    public String getDataHoraPorExtenso() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return f.format(getDataHora().getTime()).toString();
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

    public Boolean getSaidaAdiantada() {
        if (this.aluno != null && !this.entrada) {
            String turno = this.aluno.getTurno();
            switch (turno) {
                case "M":
                    return this.getDataHora().before(this.getFimHoraTurnoManha()) && this.getDataHora().after(this.getInicioHoraTurnoManha());
                case "T":
                    return this.getDataHora().before(this.getFimHoraTurnoTarde()) && this.getDataHora().after(this.getInicioHoraTurnoTarde());
                case "N":
                    return this.getDataHora().before(this.getFimHoraTurnoNoite()) && this.getDataHora().after(this.getInicioHoraTurnoNoite());
            }
        }
        return false;
    }

    public Boolean getEntradaAtrasada() {
        if (this.aluno != null && this.entrada) {
            String turno = this.aluno.getTurno();
            switch (turno) {
                case "M":
                    return this.getDataHora().after(this.getInicioHoraTurnoManha()) && this.getDataHora().before(this.getFimHoraTurnoManha());
                case "T":
                    return this.getDataHora().after(this.getInicioHoraTurnoTarde()) && this.getDataHora().before(this.getFimHoraTurnoTarde());
                case "N":
                    return this.getDataHora().after(this.getInicioHoraTurnoNoite());// && this.getDataHora().before(this.getFimHoraTurnoNoite());
            }
        }
        return false;
    }

    public long getMinutosAdiantados() {
        if (this.aluno != null && !this.entrada) {
            String turno = this.aluno.getTurno();
            switch (turno) {
                case "M":
                    return this.getFimHoraTurnoManha().getTimeInMillis() / 60000 - this.getDataHora().getTimeInMillis() / 60000;

                case "T":
                    return this.getFimHoraTurnoTarde().getTimeInMillis() / 60000 - this.getDataHora().getTimeInMillis() / 60000;

                case "N":
                    return this.getFimHoraTurnoNoite().getTimeInMillis() / 60000 - this.getDataHora().getTimeInMillis() / 60000;
            }
        }
        return 0;
    }

    public void setMinutosAdiantados(int minutosAdiantados) {
        this.minutosAdiantados = minutosAdiantados;
    }

    public long getMinutosAtrasados() {
        if (this.aluno != null && this.entrada) {
            String turno = this.aluno.getTurno();
            switch (turno) {
                case "M":
                    return this.getDataHora().getTimeInMillis() / 60000 - this.getInicioHoraTurnoManha().getTimeInMillis() / 60000;

                case "T":
                    return this.getDataHora().getTimeInMillis() / 60000 - this.getInicioHoraTurnoTarde().getTimeInMillis() / 60000;

                case "N":
                    return this.getDataHora().getTimeInMillis() / 60000 - this.getInicioHoraTurnoNoite().getTimeInMillis() / 60000;
            }
        }
        return 0;
    }

    public void setMinutosAtrasados(int minutosAtrasados) {
        this.minutosAtrasados = minutosAtrasados;
    }

    @Override
    public String toString() {
        return "Evento{" + "sequencia=" + sequencia + ", dataHora=" + dataHora.toString() + ", nomeAreaOrigem=" + nomeAreaOrigem + ", nomeAreaDestino=" + nomeAreaDestino + ", usuario=" + usuario + ", matricula=" + matricula + ", nome=" + nome + '}';
    }

}
