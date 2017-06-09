package siga;

import arquivos.Aluno;
import arquivos.Consultas;
import arquivos.Evento;
import telas.Login;
import arquivos.ExtraiDados;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author aluno
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        ExtraiDados extrair = new ExtraiDados(null, null, null);
        Consultas consultas = new Consultas(extrair.getEventos(), extrair.getAlunos());

        Map<Aluno, List<Evento>> resultado = consultas.consultaAlunosComAdiantoDeSaidaQuantidadeDeDias( null, 10, 1);

        Set<Aluno> keys = resultado.keySet();
        for (Aluno alunoMap : keys) {
            System.out.println("Aluno : " + alunoMap.getNome());
            for (Evento evento : resultado.get(alunoMap)) {
                System.out.println("    Evento: " + evento.getDataHoraPorExtenso());
            }
        }

        Calendar DataInicial = new GregorianCalendar();
        DataInicial = Calendar.getInstance();
        Calendar DataFinal = new GregorianCalendar();
        DataFinal = Calendar.getInstance();

        DataInicial.set(Calendar.DAY_OF_MONTH, 3);
        DataInicial.set(Calendar.MONTH, 3);
        DataInicial.set(Calendar.HOUR_OF_DAY, 0);
        DataInicial.set(Calendar.MINUTE, 0);
        DataInicial.set(Calendar.SECOND, 0);
        DataInicial.set(Calendar.MILLISECOND, 0);
        
        DataFinal.set(Calendar.DAY_OF_MONTH, 7);
        DataFinal.set(Calendar.MONTH, 6);
        DataFinal.set(Calendar.HOUR_OF_DAY, 0);
        DataFinal.set(Calendar.MINUTE, 0);
        DataFinal.set(Calendar.SECOND, 0);
        DataFinal.set(Calendar.MILLISECOND, 0);

        consultas.faltasConsecutivasEmDeterminadoDia(null, DataInicial, DataFinal,3,DayOfWeek.TUESDAY);

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
