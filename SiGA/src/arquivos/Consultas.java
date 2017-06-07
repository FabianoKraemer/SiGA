/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import java.util.ArrayList;
import java.util.Map;
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
    public ArrayList<Evento> consultaEventosComAtrasoDeEntrada(String Curso, String Turma, long MinutosDeAtrasoDeEntrada, Aluno aluno) {
        ArrayList<Evento> eventosResposta = new ArrayList<Evento>();
        Stream<Evento> eventosAlunosAtrasados = getEventos().stream().filter(p -> p.getEntradaAtrasada()
                && p.getMinutosAtrasados() >= MinutosDeAtrasoDeEntrada
        );

        eventosAlunosAtrasados.forEach((evento) -> eventosResposta.add(evento));
        /*for (Evento evento : alunosAtrasados.toArray(Evento[]::new)) {
            eventosResposta.add(evento);            
        }
         */

        /*Map<Aluno, ArrayList<Evento>> groupByAluno;
        groupByAluno = eventosAlunosAtrasados.collect(Collectors.groupingBy(Evento::getAluno));
        System.out.println(groupByAluno);
*/
        return eventosResposta;
    }

    /*
Consultas Baseadas em Eventos
 
Consulta 4 - Quantidade de Dias com Determinado Minutos de Atraso
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Data Inicial da Consulta;	5 - Quantidade de Minutos de Atraso; 6 - Aluno (opcional);
 
	Saída:
Lista de datas que houveram uma diferença de quantidade de Minutos superior à informada entre o Horário de Início da Disciplina e o registro de Entrada da Catraca;
 
    
Consulta 5 - Quantidade de Dias Superior ao Informado com Determinado Minutos de Atraso
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Data Inicial da Consulta;	5 - Quantidade de Minutos de Atraso; 6- Quantidade de Dias com Atraso; 7 - Aluno (opcional); 
 
	Saída:
Lista de datas que houveram uma diferença de quantidade de Minutos superior à informada entre o Horário de Início da Disciplina e o registro de Entrada da Catraca;
     
     */
    public ArrayList<Evento> consultaEventosComAdiantoDeSaida(String Curso, String Turma, long MinutosDeAdiantoDeSaida, Aluno aluno) {
        ArrayList<Evento> eventosResposta = new ArrayList<Evento>();
        Stream<Evento> eventosAlunosAdiantados = getEventos().stream().filter(p -> p.getSaidaAdiantada() && p.getMinutosAdiantados() >= MinutosDeAdiantoDeSaida);

        eventosAlunosAdiantados.forEach((evento) -> eventosResposta.add(evento));
        return eventosResposta;
    }

    /*    
    
Consulta 6 - Quantidade de Dias com Determinado Minutos de Saída Adiantada
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Aluno (opcional); 5 - Data Inicial da Consulta;	6 - Quantidade de Minutos de Saída Adiantada;
 
	Saída:
Lista de datas que houveram uma diferença de quantidade de Minutos superior à informada entre o Horário de Fim da Disciplina e o registro de Saída da Catraca;
 
Consulta 7 - Quantidade de Dias Superior ao Informado com Determinado Minutos de Saída Adiantada
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Aluno (opcional); 5 - Data Inicial da Consulta;	6 - Quantidade de Minutos de Saída Adiantada; 7 - Quantidade de Dias de Saída Adiantada;
 
	Saída:
Lista de datas que houveram uma diferença de quantidade de Minutos superior à informada entre o Horário de Fim da Disciplina e o registro de Saída da Catraca;
     */
}
