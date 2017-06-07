/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author francisco.freire
 */
public class Consultas {

    private ArrayList<Evento> eventos;
    private ArrayList<Aluno> alunos;

    public Consultas(ArrayList<Evento> eventos, ArrayList<Aluno> alunos) {
        this.eventos = eventos;
        this.alunos = alunos;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    /*    
    Consultas
Consultas Baseadas em Aluno
Consulta 1 - Faltas Consecutivas
	Entradas:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Aluno; 5 - Data Inicial da Consulta; 6 - Quantidade Dias Consecutivos de Falta;
	Saída:
		Lista de datas com dias consecutivos em que o Aluno não teve registros de Entrada ou Saída em Datas Consecutivas e que não seja Sábado ou Domingo;0,
	
Consulta 2 - Faltas Consecutivas em Determinado Dia da Semana
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Data Inicial da Consulta;		5 - Quantidade de Faltas Consecutivas por Dia da Semana;  6 - Aluno (opcional);
 
	Saída:
		Lista de datas que houveram faltas consecutivas em Disciplinas que possuem horário no dia da semana informado;
 
Consulta 3 - Quantidade Total de Faltas
	Entrada:
	1 - Curso; 2 - Turma; 3 - Disciplina;4 - Data Inicial da Consulta;	5 - Quantidade Total de Faltas; 6 - Aluno (opcional);
 
	Saída:
		Lista de datas que houveram faltas que não seja Sábado ou Domingo;
     */
    
    
    
    //Consultas Baseadas em Eventos
    private List<Evento> consultaEventosComAtrasoDeEntrada(String Curso, String Turma, long MinutosDeAtrasoDeEntrada) {

        List<Evento> eventosResposta = getEventos().stream().filter(p -> p.getEntradaAtrasada()
                && p.getMinutosAtrasados() >= MinutosDeAtrasoDeEntrada
        ).collect(Collectors.toList());

        return eventosResposta;
    }

    private List<Evento> consultaEventosComAdiantoDeSaida(String Curso, String Turma, long MinutosDeAdiantoDeSaida) {

        List<Evento> eventosResposta = getEventos().stream().filter(p -> p.getSaidaAdiantada()
                && p.getMinutosAdiantados() >= MinutosDeAdiantoDeSaida).collect(Collectors.toList());

        return eventosResposta;

    }

    /*
    
 
Consulta 4 - Quantidade de Dias com Determinado Minutos de Atraso
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Data Inicial da Consulta;	5 - Quantidade de Minutos de Atraso; 6 - Aluno (opcional);
 
	Saída:
Lista de datas que houveram uma diferença de quantidade de Minutos superior à informada entre o Horário de Início da Disciplina e o registro de Entrada da Catraca;
 
     */
    public Map<Aluno, List<Evento>> consultaAlunosComAtrasoDeEntrada(String Curso, String Turma, long MinutosDeAtrasoDeEntrada) {

        List<Evento> listaEventos = consultaEventosComAtrasoDeEntrada(Curso, Turma, MinutosDeAtrasoDeEntrada);

        Map<Aluno, List<Evento>> groupByAluno = listaEventos.stream().collect(Collectors.groupingBy(Evento::getAluno));

        /*
        Set<Aluno> keys = groupByAluno.keySet();
        for (Aluno alunoMap : keys) {
            System.out.println("Aluno : " + alunoMap.getNome());
            for (Evento evento : groupByAluno.get(alunoMap))
            {
                System.out.println("    Evento: " + evento.getDataHoraPorExtenso());
            }
        }
         */
        return groupByAluno;
    }

    /*

Consulta 5 - Quantidade de Dias Superior ao Informado com Determinado Minutos de Atraso
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Data Inicial da Consulta;	5 - Quantidade de Minutos de Atraso; 6- Quantidade de Dias com Atraso; 7 - Aluno (opcional); 
 
	Saída:
Lista de datas que houveram uma diferença de quantidade de Minutos superior à informada entre o Horário de Início da Disciplina e o registro de Entrada da Catraca;
     
     */
    public Map<Aluno, List<Evento>> consultaAlunosComAtrasoDeEntradaQuantidadeDeDias(String Curso, String Turma, long MinutosDeAtrasoDeEntrada, int quantidadeDeDias) {

        List<Evento> listaEventos = consultaEventosComAtrasoDeEntrada(Curso, Turma, MinutosDeAtrasoDeEntrada);

        Map<Aluno, List<Evento>> groupByAluno = listaEventos.stream().collect(Collectors.groupingBy(Evento::getAluno));

        /*
        Set<Aluno> keys = groupByAluno.keySet();
        for (Aluno alunoMap : keys) {
            System.out.println("Aluno : " + alunoMap.getNome());
            for (Evento evento : groupByAluno.get(alunoMap))
            {
                System.out.println("    Evento: " + evento.getDataHoraPorExtenso());
            }
        }
         */
        //Remove Alunos que ainda não tem a quantidade de dias especificado
        for (Iterator<Map.Entry<Aluno, List<Evento>>> it = groupByAluno.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Aluno, List<Evento>> entry = it.next();
            if (entry.getValue().size() < quantidadeDeDias) {
                it.remove();
            }
        }
        return groupByAluno;
    }

    /*    
    
Consulta 6 - Quantidade de Dias com Determinado Minutos de Saída Adiantada
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Aluno (opcional); 5 - Data Inicial da Consulta;	6 - Quantidade de Minutos de Saída Adiantada;
 
	Saída:
Lista de datas que houveram uma diferença de quantidade de Minutos superior à informada entre o Horário de Fim da Disciplina e o registro de Saída da Catraca;
     */
    public Map<Aluno, List<Evento>> consultaAlunosComAdiantoDeSaida(String Curso, String Turma, long MinutosDeAtrasoDeEntrada) {

        List<Evento> listaEventos = consultaEventosComAdiantoDeSaida(Curso, Turma, MinutosDeAtrasoDeEntrada);

        Map<Aluno, List<Evento>> groupByAluno = listaEventos.stream().collect(Collectors.groupingBy(Evento::getAluno));

        return groupByAluno;
    }

    /*
Consulta 7 - Quantidade de Dias Superior ao Informado com Determinado Minutos de Saída Adiantada
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Aluno (opcional); 5 - Data Inicial da Consulta;	6 - Quantidade de Minutos de Saída Adiantada; 7 - Quantidade de Dias de Saída Adiantada;
 
	Saída:
Lista de datas que houveram uma diferença de quantidade de Minutos superior à informada entre o Horário de Fim da Disciplina e o registro de Saída da Catraca;
     */
    public Map<Aluno, List<Evento>> consultaAlunosComAdiantoDeSaidaQuantidadeDeDias(String Curso, String Turma, long MinutosDeAtrasoDeEntrada, int quantidadeDeDias) {

        List<Evento> listaEventos = consultaEventosComAdiantoDeSaida(Curso, Turma, MinutosDeAtrasoDeEntrada);

        Map<Aluno, List<Evento>> groupByAluno = listaEventos.stream().collect(Collectors.groupingBy(Evento::getAluno));

        //Remove Alunos que ainda não tem a quantidade de dias especificado
        for (Iterator<Map.Entry<Aluno, List<Evento>>> it = groupByAluno.entrySet().iterator(); it.hasNext();) {
            Map.Entry<Aluno, List<Evento>> entry = it.next();
            if (entry.getValue().size() < quantidadeDeDias) {
                it.remove();
            }
        }
        return groupByAluno;
    }
}
