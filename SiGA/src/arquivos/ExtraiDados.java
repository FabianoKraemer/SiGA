/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author Fabiano
 */
public class ExtraiDados {

    private String dbCatracaUsuarios;
    private String dbAcademico;
    private String dbCatracaEventos;

    private ArrayList<Evento> eventos;
    private ArrayList<Aluno> alunos;
    private ArrayList<String> turmas;
    private ArrayList<String> diasDaSemana;

    public ArrayList<String> getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(ArrayList<String> diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }

    public ArrayList<String> getTurmas() {
        return turmas;
    }

    public void setTurmas(ArrayList<String> turmas) {
        this.turmas = turmas;
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

    public ExtraiDados(String dbCatracaUsuarios, String dbAcademico, String dbCatracaEventos) {
        this.dbCatracaUsuarios = dbCatracaUsuarios;
        this.dbAcademico = dbAcademico;
        this.dbCatracaEventos = dbCatracaEventos;
        this.criaArrays();
    }

    public ArrayList<Aluno> ProcessaArquivos(ArrayList<String> CatracaAluno, ArrayList<String> Academico, ArrayList<Evento> Eventos) {
        ArrayList<Aluno> Alunos = new ArrayList<Aluno>();

        for (String linha : CatracaAluno) {
            //Estrutura CatracaAluno: CODIGO,MATRICULA,TURMA,NOME,VALIDADEACESSO,PERMISSAO
            String[] partesCatracaAluno = linha.split(Pattern.quote(","));

            Aluno aluno = null;

            for (String linhaAcademico : Academico) {
                // Estrutura Academico: NR_MATRICULA	NM_PESSOA	ID_TURMA	CD_TURNO	SITUACAO
                String[] partesAcademico = linhaAcademico.split(Pattern.quote(","));

                // System.out.println(partesCatracaAluno[3] + " - " + partesAcademico[1]);
                if (partesCatracaAluno[3].equals(partesAcademico[1])) {
                    //System.out.println(partesCatracaAluno[3]);
                    //String nome, String matricula, String turma, String codigo, String validadeAcesso, String permissao, String 
                    aluno = new Aluno(partesCatracaAluno[3], partesCatracaAluno[1], partesCatracaAluno[2], partesCatracaAluno[0], partesCatracaAluno[4], partesCatracaAluno[5], partesAcademico[4], partesAcademico[3]);
                    Alunos.add(aluno);
                    break;
                }

            }

            if (aluno != null) {
                for (Evento evento : Eventos) {
                    if (evento.getNome().equals(aluno.getNome())) {
                        //System.out.println(aluno.nome);
                        evento.setAluno(aluno);

                        /* if (!evento.getEntrada()) {
                            if (evento.getSaidaAdiantada()) {
                                System.out.println(aluno.getNome() + " Saída Adiantada ");
                                System.out.println("Minutos Adiantado? " + evento.getMinutosAdiantados());
                            }
                        } else {
                            if (evento.getEntradaAtrasada()) {
                                System.out.println(aluno.getNome() + " Entrada Atrasada ");
                                System.out.println("Minutos Atrasado? " + evento.getMinutosAtrasados());
                            }
                        }*/
                        try {
                            aluno.getEventos().add(evento);
                            //aluno.eventos.add(evento);
                        } catch (Exception ex) {
                            System.out.println(ex.toString());
                        };

                    }

                }
            }
        }

        //Ordena Alunos
        Alunos.sort((p1, p2) -> p1.compareTo(p2));

        return Alunos;

    }

    public ArrayList<String> ExtraiArquivoTexto(String localizacaoArquivo) {

        ArrayList<String> resposta = new ArrayList<String>();

        try {
            File arquivo = new File(localizacaoArquivo);
            Scanner leitor = new Scanner(arquivo);
            String linha = leitor.nextLine();

            while (leitor.hasNextLine()) {
                linha = leitor.nextLine();
                resposta.add(linha);
            }

        } catch (Exception ex) {
            System.err.println("erro: " + ex.toString());
        }

        return resposta;
    }

    public ArrayList<String> ProcessaTurmas(ArrayList<String> Academico) {
        ArrayList<String> turmas = new ArrayList<String>();

        for (String linha : Academico) {
            // Estrutura Academico: NR_MATRICULA	NM_PESSOA	ID_TURMA	CD_TURNO	SITUACAO
            String[] partesAcademico = linha.split(Pattern.quote(","));
            if (!turmas.contains(partesAcademico[2])) {
                turmas.add(partesAcademico[2]);
            }
        }

        turmas.sort((p1, p2) -> p1.compareTo(p2));
        return turmas;
    }

    public ArrayList<Evento> ProcessaEventos(ArrayList<String> CatracaEventos) {
        ArrayList<Evento> eventos = new ArrayList<Evento>();

        //String sequencia, String dataHora, String nomeAreaOrigem, String nomeAreaDestino, String usuario, String matricula, String nome)
        for (String linha : CatracaEventos) {
            //Estrutura catraca-eventos: SEQUENCIA, DATAHORA, NOMEAREAORIGEM, USUARIO, MATRICULA, NOME
            String[] partesCatracaEvento = linha.split(Pattern.quote(","));
            Evento evento = new Evento(partesCatracaEvento[0], partesCatracaEvento[1], partesCatracaEvento[2], partesCatracaEvento[3], partesCatracaEvento[4], partesCatracaEvento[5], partesCatracaEvento[6]);
            //System.out.println(evento.toString());
            eventos.add(evento);
        }

        return eventos;
    }

    public Aluno consultaAlunoPorNome(String nome) {
        
        for (int contador=0; contador< getAlunos().size(); contador++)
        {
            Aluno aluno = getAlunos().get(contador);
            if (aluno.getNome().equals(nome)) {
                return aluno;                
            }
        
        }
        
        return null;
    }

    public void criaArrays() {

        ArrayList<String> diasDaSemana = new ArrayList<String>();
        diasDaSemana.add("Segunda-Feira");
        diasDaSemana.add("Terça-Feira");
        diasDaSemana.add("Quarta-Feira");
        diasDaSemana.add("Quinta-Feira");
        diasDaSemana.add("Sexta-Feira");
        setDiasDaSemana(diasDaSemana);

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        ArrayList<String> CatracaAluno = new ArrayList<String>();
        ArrayList<String> Academico = new ArrayList<String>();
        ArrayList<String> CatracaEventos = new ArrayList<String>();

        //o endereço do arquivo será o this.string de cada um, enviado pelo método que chama essa classe
        CatracaAluno = ExtraiArquivoTexto("db-catraca-usuarios.csv");
        Academico = ExtraiArquivoTexto("db-academico.csv");
        CatracaEventos = ExtraiArquivoTexto("db-catraca-eventos.csv");

        ArrayList<Evento> eventos = new ArrayList<Evento>();
        eventos = ProcessaEventos(CatracaEventos);

        alunos = ProcessaArquivos(CatracaAluno, Academico, eventos);

        setAlunos(alunos);
        setEventos(eventos);
        setTurmas(ProcessaTurmas(Academico));
        System.out.println("Alunos lidos de arquivos: " + alunos.size());

    }

}
