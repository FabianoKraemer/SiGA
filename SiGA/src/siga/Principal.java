package siga;

import arquivos.Consultas;
import telas.Login;
import arquivos.ExtraiDados;

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
        
        ExtraiDados extrair = new ExtraiDados(null,null,null);
        Consultas consultas = new Consultas(extrair.getEventos(), extrair.getAlunos());
        
        
        System.out.println(" Eventos com 10 minutos de atraso: " + consultas.consultaEventosComAtrasoDeEntrada(null, null, 10, null).toString());        
        System.out.println(" Eventos com 20 minutos de atraso: " + consultas.consultaEventosComAtrasoDeEntrada(null, null, 20, null).toString());        
        System.out.println(" Eventos com 30 minutos de atraso: " + consultas.consultaEventosComAtrasoDeEntrada(null, null, 30, null).toString());        
        System.out.println(" Eventos com 40 minutos de atraso: " + consultas.consultaEventosComAtrasoDeEntrada(null, null, 40, null).toString());        
        
        /*System.out.println(" Eventos com 10 minutos de adianto: " + consultas.consultaEventosComAdiantoDeSaida(null, null, 10, null).toString());
        System.out.println(" Eventos com 20 minutos de adianto: " + consultas.consultaEventosComAdiantoDeSaida(null, null, 10, null).toString());
        System.out.println(" Eventos com 30 minutos de adianto: " + consultas.consultaEventosComAdiantoDeSaida(null, null, 10, null).toString());
        System.out.println(" Eventos com 40 minutos de adianto: " + consultas.consultaEventosComAdiantoDeSaida(null, null, 10, null).toString());
        */
        
        //Login login = new Login();
               
        //login.setVisible(true);
        
    }
    
}
