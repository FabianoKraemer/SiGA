package siga;

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
        
        ExtraiDados extrair = new ExtraiDados("1","2","3");
        
        Login login = new Login();
               
        login.setVisible(true);
        
    }
    
}
