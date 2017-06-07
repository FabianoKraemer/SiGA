package siga;

import arquivos.Aluno;
import arquivos.Consultas;
import arquivos.Evento;
import telas.Login;
import arquivos.ExtraiDados;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author aluno
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ExtraiDados extrair = new ExtraiDados(null, null, null);
        Consultas consultas = new Consultas(extrair.getEventos(), extrair.getAlunos());

        Map<Aluno, List<Evento>> resultado = consultas.consultaAlunosComAdiantoDeSaidaQuantidadeDeDias(null, null, 10, 1);

        Set<Aluno> keys = resultado.keySet();
        for (Aluno alunoMap : keys) {
            System.out.println("Aluno : " + alunoMap.getNome());
            for (Evento evento : resultado.get(alunoMap)) {
                System.out.println("    Evento: " + evento.getDataHoraPorExtenso());
            }
        }
        /*System.out.println(" Eventos com 10 minutos de atraso: " + consultas.consultaEventosComAtrasoDeEntrada(null, null, 10).toString());        
        System.out.println(" Eventos com 20 minutos de atraso: " + consultas.consultaEventosComAtrasoDeEntrada(null, null, 20).toString());        
        System.out.println(" Eventos com 30 minutos de atraso: " + consultas.consultaEventosComAtrasoDeEntrada(null, null, 30).toString());        
        System.out.println(" Eventos com 40 minutos de atraso: " + consultas.consultaEventosComAtrasoDeEntrada(null, null, 40).toString());        
         */
 /*System.out.println(" Eventos com 10 minutos de adianto: " + consultas.consultaEventosComAdiantoDeSaida(null, null, 10, null).toString());
        System.out.println(" Eventos com 20 minutos de adianto: " + consultas.consultaEventosComAdiantoDeSaida(null, null, 10, null).toString());
        System.out.println(" Eventos com 30 minutos de adianto: " + consultas.consultaEventosComAdiantoDeSaida(null, null, 10, null).toString());
        System.out.println(" Eventos com 40 minutos de adianto: " + consultas.consultaEventosComAdiantoDeSaida(null, null, 10, null).toString());
         */

        //Login login = new Login();
        //login.setVisible(true);
    }

}
