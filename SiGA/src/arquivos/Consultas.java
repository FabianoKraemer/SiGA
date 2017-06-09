/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
     */
    private static List<Calendar> getDiasCorridosEntreDuasDatas(Calendar startdate, Calendar enddate) {
        List<Calendar> dates = new ArrayList<Calendar>();
        Calendar calendar = new GregorianCalendar();
        calendar = startdate;

        while (calendar.before(enddate)) {
            dates.add((Calendar) calendar.clone());
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    private static Calendar encontraPrimeiroDiaDaSemanaAPartirDeUmaData(Calendar startDate, Calendar endDate, DayOfWeek DiaDaSemana) {
        Calendar calendar = new GregorianCalendar();
        calendar = startDate;

        if (calendar.get(Calendar.DAY_OF_WEEK) == (DiaDaSemana.getValue() + 1)) {
            return calendar;
        } else {
            while (calendar.get(Calendar.DAY_OF_WEEK) != (DiaDaSemana.getValue() + 1)) {
                calendar.add(Calendar.DATE, 1);
            }
            if (calendar.get(Calendar.DAY_OF_WEEK) == (DiaDaSemana.getValue() + 1) && calendar.before(endDate)) {
                return calendar;
            }
        }

        return null;
    }

    private static List<Calendar> getDiasAlternadosEntreDuasDatas(Calendar startdate, Calendar enddate, DayOfWeek DiaDaSemana) {
        List<Calendar> dates = new ArrayList<Calendar>();
        Calendar calendar = new GregorianCalendar();
        calendar = encontraPrimeiroDiaDaSemanaAPartirDeUmaData(startdate, enddate, DiaDaSemana);

        if (calendar != null) {
            while (calendar.before(enddate)) {
                dates.add((Calendar) calendar.clone());
                calendar.add(Calendar.DATE, 7);
            }
            return dates;

        }
        return null;
    }

    public static String getDataHoraPorExtenso(Calendar calendario) {
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return f.format(calendario.getTime()).toString();
    }

    private Map<Aluno, List<Calendar>> listaDiasComFaltas(Calendar DataInicial, Calendar DataFinal, DayOfWeek DiaDaSemana) {

        Map<Aluno, List<Calendar>> mapaResposta = new HashMap<Aluno, List<Calendar>>();
        List<Calendar> diasSelecionados;

        if (DiaDaSemana == null) {
            diasSelecionados = getDiasCorridosEntreDuasDatas(DataInicial, DataFinal);
        } else {
            diasSelecionados = getDiasAlternadosEntreDuasDatas(DataInicial, DataFinal, DiaDaSemana);
        }

        getAlunos().forEach((aluno) -> {
            for (int contador = 0; contador < diasSelecionados.size(); contador++) {
                Calendar dia = diasSelecionados.get(contador);

                if (dia.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && dia.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    Boolean encontrado = false;

                    for (int contadorEventos = 0; contadorEventos < aluno.getEventos().size(); contadorEventos++) {
                        Evento evento = aluno.getEventos().get(contadorEventos);
                        if (evento.getData().get(Calendar.YEAR) == dia.get(Calendar.YEAR)
                                && evento.getData().get(Calendar.MONTH) == dia.get(Calendar.MONTH)
                                && evento.getData().get(Calendar.DAY_OF_MONTH) == dia.get(Calendar.DAY_OF_MONTH)) {
                            encontrado = true;
                            break;
                        };
                    };

                    if (!encontrado) {
                        if (mapaResposta.get(aluno) == null) {
                            ArrayList<Calendar> listaCalendario = new ArrayList<Calendar>();
                            mapaResposta.put(aluno, listaCalendario);
                        }
                        mapaResposta.get(aluno).add(dia);
                        //System.out.println(aluno.getNome() + " --- " + getDataHoraPorExtenso(dia).toString());
                    }
                }
            };
        });

        return mapaResposta;
    }

    /*    
    Consulta 1 - Faltas Consecutivas
	Entradas:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Aluno; 5 - Data Inicial da Consulta; 6 - Quantidade Dias Consecutivos de Falta;
	Saída:
		Lista de datas com dias consecutivos em que o Aluno não teve registros de Entrada ou Saída em Datas Consecutivas e que não seja Sábado ou Domingo;0,
	
     */
    public Map<Aluno, List<Calendar>> listaFaltas(String Turma, String Aluno, Calendar DataInicial, Calendar DataFinal, int QuantidadeFaltas, DayOfWeek DiaDaSemana, Boolean Consecutiva) {
        Map<Aluno, List<Calendar>> resposta = listaDiasComFaltas((Calendar)DataInicial.clone(), (Calendar)DataFinal.clone(), DiaDaSemana);

        if (Consecutiva) {
            //Remove Alunos que ainda não tem a quantidade de dias especificado
            for (Iterator<Map.Entry<Aluno, List<Calendar>>> it = resposta.entrySet().iterator(); it.hasNext();) {
                Map.Entry<Aluno, List<Calendar>> entry = it.next();
                List<Calendar> listaDiasConsecutivosResposta = new ArrayList<Calendar>();
                List<Calendar> listaDiasConsecutivos = new ArrayList<Calendar>();
                List<Calendar> listaDias = entry.getValue();

                for (Calendar calendario : listaDias) {
                    if (listaDiasConsecutivos.size() == 0) {
                        listaDiasConsecutivos.add((Calendar) calendario.clone());
                    } else {
                        Calendar ultimoDia = listaDiasConsecutivos.get(listaDiasConsecutivos.size() - 1);
                        ultimoDia.add(Calendar.DAY_OF_MONTH, 1);
                        if (calendario.equals(ultimoDia)) {
                            listaDiasConsecutivos.add((Calendar) calendario.clone());
                        } else {

                            if (listaDiasConsecutivosResposta.size() < listaDiasConsecutivos.size()) {
                                listaDiasConsecutivosResposta = listaDiasConsecutivos;
                            }
                            listaDiasConsecutivos.clear();
                        }
                        if (listaDiasConsecutivosResposta.size() < listaDiasConsecutivos.size()) {
                            listaDiasConsecutivosResposta.clear();
                            for (Calendar calendarioCons : listaDiasConsecutivos) {
                                listaDiasConsecutivosResposta.add((Calendar) calendarioCons.clone());
                            }
                        };
                    }
                }
                if (listaDiasConsecutivosResposta.size() < QuantidadeFaltas) {
                    it.remove();
                } else if (Aluno != null && !entry.getKey().getNome().equals(Aluno)) {
                    it.remove();
                } else if (Turma != null && !entry.getKey().getTurma().equals(Turma)) {
                    it.remove();
                }

                /* for (Calendar evento : entry.getValue()) {
                System.out.println( entry.getKey().getNome() + "    Evento: " + getDataHoraPorExtenso(evento));
            }*/
            }
        }else
        {
         for (Iterator<Map.Entry<Aluno, List<Calendar>>> it = resposta.entrySet().iterator(); it.hasNext();) {
                Map.Entry<Aluno, List<Calendar>> entry = it.next();
                if (Aluno != null && !entry.getKey().getNome().equals(Aluno)) {
                    it.remove();
                } else if (Turma != null && !entry.getKey().getTurma().equals(Turma)) {
                    it.remove();
                }
         }
        
        }
        
        Set<Aluno> keys = resposta.keySet();
        for (Aluno alunoMap : keys) {
            System.out.println("Aluno : " + alunoMap.getNome());
            for (Calendar evento : resposta.get(alunoMap)) {
                System.out.println("    Evento: " + getDataHoraPorExtenso(evento));
            }
        }

        return resposta;
    }

    /*
    Consulta 2 - Faltas Consecutivas em Determinado Dia da Semana
	Entrada:
		1 - Curso; 2 - Turma; 3 - Disciplina; 4 - Data Inicial da Consulta;		5 - Quantidade de Faltas Consecutivas por Dia da Semana;  6 - Aluno (opcional);
 
	Saída:
		Lista de datas que houveram faltas consecutivas em Disciplinas que possuem horário no dia da semana informado;
     */

    /*
 
    Consulta 3 - Quantidade Total de Faltas
	Entrada:
	1 - Curso; 2 - Turma; 3 - Disciplina;4 - Data Inicial da Consulta;	5 - Quantidade Total de Faltas; 6 - Aluno (opcional);
 
	Saída:
		Lista de datas que houveram faltas que não seja Sábado ou Domingo;
     */
    public Map<Aluno, List<Calendar>> quantidadeTotalDiasComFaltas(String Turma, Calendar DataInicial, Calendar DataFinal) {

        return listaDiasComFaltas(DataInicial, DataFinal, null);
    }

    //Consultas Baseadas em Eventos
    private List<Evento> consultaEventosComAtrasoDeEntrada(String Turma, long MinutosDeAtrasoDeEntrada) {

        List<Evento> eventosResposta = getEventos().stream().filter(p -> p.getEntradaAtrasada()
                && p.getMinutosAtrasados() >= MinutosDeAtrasoDeEntrada
        ).collect(Collectors.toList());

        return eventosResposta;
    }

    private List<Evento> consultaEventosComAdiantoDeSaida(String Turma, long MinutosDeAdiantoDeSaida) {

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
    public Map<Aluno, List<Evento>> consultaAlunosComAtrasoDeEntrada(String Turma, long MinutosDeAtrasoDeEntrada) {

        List<Evento> listaEventos = consultaEventosComAtrasoDeEntrada(Turma, MinutosDeAtrasoDeEntrada);

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
    public Map<Aluno, List<Evento>> consultaAlunosComAtrasoDeEntradaQuantidadeDeDias(String Turma, long MinutosDeAtrasoDeEntrada, int quantidadeDeDias) {

        List<Evento> listaEventos = consultaEventosComAtrasoDeEntrada(Turma, MinutosDeAtrasoDeEntrada);

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
    public Map<Aluno, List<Evento>> consultaAlunosComAdiantoDeSaida(String Turma, long MinutosDeAtrasoDeEntrada) {

        List<Evento> listaEventos = consultaEventosComAdiantoDeSaida(Turma, MinutosDeAtrasoDeEntrada);

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
    public Map<Aluno, List<Evento>> consultaAlunosComAdiantoDeSaidaQuantidadeDeDias(String Turma, long MinutosDeAtrasoDeEntrada, int quantidadeDeDias) {

        List<Evento> listaEventos = consultaEventosComAdiantoDeSaida(Turma, MinutosDeAtrasoDeEntrada);

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
