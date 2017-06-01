package siga;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author aluno
 */
public class TesteArquivos {

    public static ArrayList<Aluno> ProcessaArquivos(ArrayList<String> CatracaAluno, ArrayList<String> Academico) {
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        for (String linha : CatracaAluno) {
            //Estrutura CatracaAluno: CODIGO,MATRICULA,TURMA,NOME,VALIDADEACESSO,PERMISSAO
            String[] partesCatracaAluno = linha.split(Pattern.quote(","));

            for (String linhaAcademico : Academico) {
                // Estrutura Academico: NR_MATRICULA	NM_PESSOA	ID_TURMA	CD_TURNO	SITUACAO
                String[] partesAcademico = linhaAcademico.split(Pattern.quote(","));

                // System.out.println(partesCatracaAluno[3] + " - " + partesAcademico[1]);
                if (partesCatracaAluno[3].equals(partesAcademico[1].toString().trim())) {
                    //System.out.println(partesCatracaAluno[3]);
                    //String nome, String matricula, String turma, String codigo, String validadeAcesso, String permissao, String 
                    Aluno aluno = new Aluno(partesCatracaAluno[3].toString(), partesCatracaAluno[1].toString(),  partesCatracaAluno[2].toString(), partesCatracaAluno[0].toString(), partesCatracaAluno[4].toString(), partesCatracaAluno[5].toString(), partesAcademico[4].toString(),partesAcademico[3].toString());        
                    alunos.add(aluno);
                    break;
                }
            }

            
        }

        // System.out.println(aluno.toString());
        return alunos;

    }

    public static ArrayList<String> ExtraiArquivoTexto(String localizacaoArquivo) {

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

    public static void main(String[] args) {

        ArrayList<Aluno> alunos = new ArrayList<Aluno>();

        ArrayList<String> CatracaAluno = new ArrayList<String>();
        ArrayList<String> Academico = new ArrayList<String>();

        CatracaAluno = ExtraiArquivoTexto("db-catraca-usuarios.csv");
        Academico = ExtraiArquivoTexto("db-academico.csv");

        alunos = ProcessaArquivos(CatracaAluno, Academico);

        System.out.println(alunos.size());
        
    }

}
