/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import arquivos.Alerta;
import arquivos.Aluno;
import arquivos.Consultas;
import arquivos.Evento;
import arquivos.ExtraiDados;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aluno
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    private String usuarioLogado;

    private ArrayList<Alerta> Alertas = new ArrayList<Alerta>();
    private ExtraiDados extrair = new ExtraiDados(null, null, null);
    private Consultas consultas = new Consultas(extrair.getEventos(), extrair.getAlunos());

    public Principal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLUsuario = new javax.swing.JLabel();
        jLUsuarioLogado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBCriarFiltros = new javax.swing.JButton();
        jBGerarRelatorios = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jBCriarFiltros.setText("Criar Alertas");
        jBCriarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCriarFiltrosActionPerformed(evt);
            }
        });

        jBGerarRelatorios.setText("Gerar Relatórios");
        jBGerarRelatorios.setMaximumSize(new java.awt.Dimension(125, 25));
        jBGerarRelatorios.setMinimumSize(new java.awt.Dimension(125, 25));
        jBGerarRelatorios.setPreferredSize(new java.awt.Dimension(130, 27));
        jBGerarRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBGerarRelatoriosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLUsuario)
                                .addGap(63, 63, 63)
                                .addComponent(jLUsuarioLogado))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBCriarFiltros)
                                .addGap(18, 18, 18)
                                .addComponent(jBGerarRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(585, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLUsuario)
                    .addComponent(jLUsuarioLogado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBCriarFiltros)
                    .addComponent(jBGerarRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        this.setSize(1280, 720);

    }//GEN-LAST:event_formWindowActivated

    private void jBCriarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCriarFiltrosActionPerformed
        // TODO add your handling code here:
        CadastrarAlerta cadastrarAlerta = new CadastrarAlerta(this, "Criar Alerta", rootPaneCheckingEnabled);
        //this.setEnabled(false);
        cadastrarAlerta.setVisible(true);

        if (cadastrarAlerta.getAlerta() != null) {
            Alertas.add(cadastrarAlerta.getAlerta());
        }
        processaAlertas();
    }//GEN-LAST:event_jBCriarFiltrosActionPerformed

    private void jBGerarRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBGerarRelatoriosActionPerformed
        // TODO add your handling code here:
        iText();
        
        


    }//GEN-LAST:event_jBGerarRelatoriosActionPerformed

    private void iText(){
        Document documento = new Document();
        
        DefaultTableModel modelo =  (DefaultTableModel)jTable1.getModel();
      
        String linha[] = new String[6];
                   
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("documento.pdf"));
            
            documento.open();
            
            documento.add(new Paragraph("            Nome             |  Matricula  |   Turma  |   Turno  |   Data  |   Informação"));
            
            for(int i = 0; i < modelo.getRowCount(); i++){
                String row = modelo.getValueAt(i, 0).toString() + "  |  " +
                             modelo.getValueAt(i, 1).toString() + "   |  " +
                             modelo.getValueAt(i, 2).toString() + " | " +
                             modelo.getValueAt(i, 3).toString() + " |  " +
                             modelo.getValueAt(i, 4).toString() + " | " +
                             modelo.getValueAt(i, 5).toString();
                documento.add(new Paragraph(row));              
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            documento.close();
        }
        
        //abrindo documento
        try {
            Desktop.getDesktop().open(new File("documento.pdf"));
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    private void processaAlertas() {
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                null,
                new String[]{"Nome", "Matrícula", "Turma", "Turno", "Data", "Informação"}
        ));
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();

        modelo.setRowCount(0);

        for (int contador = 0; contador < Alertas.size(); contador++) {
            Alerta alerta = Alertas.get(contador);
            Map<Aluno, List<Evento>> respostaConsultaEvento = new HashMap<Aluno, List<Evento>>();
            Map<Aluno, List<Calendar>> respostaConsultaCalendar = new HashMap<Aluno, List<Calendar>>();

            switch (alerta.getTipoAlerta()) {
                case 0: //consulta Faltas por aluno
                    if (alerta.isDiaEspecificoEscolhido()) {
                        respostaConsultaCalendar = consultas.listaFaltas(alerta.getTurma(), alerta.getNomeAluno(), alerta.getDataInicial(), alerta.getDataFinal(), alerta.getQuantidadeMinimaDeDiasDeFalta(), DayOfWeek.of(alerta.getDiaEspecifico()), alerta.isConsecutivo());
                    } else {
                        respostaConsultaCalendar = consultas.listaFaltas(alerta.getTurma(), alerta.getNomeAluno(), alerta.getDataInicial(), alerta.getDataFinal(), alerta.getQuantidadeMinimaDeDiasDeFalta(), null, alerta.isConsecutivo());
                    }

                    break;
                case 1: // consulta Faltas por turma
                    if (alerta.isDiaEspecificoEscolhido()) {
                        respostaConsultaCalendar = consultas.listaFaltas(alerta.getTurma(), alerta.getNomeAluno(), alerta.getDataInicial(), alerta.getDataFinal(), alerta.getQuantidadeMinimaDeDiasDeFalta(), DayOfWeek.of(alerta.getDiaEspecifico()), alerta.isConsecutivo());
                    } else {
                        respostaConsultaCalendar = consultas.listaFaltas(alerta.getTurma(), alerta.getNomeAluno(), alerta.getDataInicial(), alerta.getDataFinal(), alerta.getQuantidadeMinimaDeDiasDeFalta(), null, alerta.isConsecutivo());
                    }
                    break;
                case 2: // consulta Atrasos
                    respostaConsultaEvento = consultas.consultaAlunosComAtrasoDeEntradaQuantidadeDeDias(null, alerta.getMinutosAtraso(), alerta.getQuantidadeMinimaDeDiasDeAtraso());
                    break;
                case 3: // consulta Adiantos
                    respostaConsultaEvento = consultas.consultaAlunosComAdiantoDeSaidaQuantidadeDeDias(null, alerta.getMinutosAdianto(), alerta.getQuantidadeMinimaDeDiasDeAdianto());
                    break;
            }

            if (respostaConsultaEvento.size() > 0) {
                Set<Aluno> keys = respostaConsultaEvento.keySet();
                for (Aluno alunoMap : keys) {
                    List<Evento> eventos = new ArrayList<Evento>();
                    eventos = respostaConsultaEvento.get(alunoMap);
                    for (int i = 0; i < eventos.size(); i++) {
                        Evento evento = eventos.get(i);
                        String linha[] = new String[6];
                        linha[0] = alunoMap.getNome();
                        linha[1] = alunoMap.getMatricula();
                        linha[2] = alunoMap.getTurma();
                        linha[3] = alunoMap.getTurno();
                        linha[4] = evento.getDataHoraPorExtenso();
                        linha[5] = alerta.getTipoAlertaDescricao();
                        modelo.addRow(linha);
                    }

                }
            } else if (respostaConsultaCalendar.size() > 0) {
                Set<Aluno> keys = respostaConsultaCalendar.keySet();
                for (Aluno alunoMap : keys) {
                    List<Calendar> dias = new ArrayList<Calendar>();
                    dias = respostaConsultaCalendar.get(alunoMap);
                    for (int i = 0; i < dias.size(); i++) {
                        Calendar dia = dias.get(i);
                        String linha[] = new String[6];
                        linha[0] = alunoMap.getNome();
                        linha[1] = alunoMap.getMatricula();
                        linha[2] = alunoMap.getTurma();
                        linha[3] = alunoMap.getTurma();
                        linha[4] = consultas.getDataHoraPorExtenso(dia);
                        linha[5] = alerta.getTipoAlertaDescricao();
                        modelo.addRow(linha);
                    }

                }

            }
        }
    }

    public void usuario(String usuario) {
        this.usuarioLogado = usuario;
        jLUsuario.setText(this.usuarioLogado);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCriarFiltros;
    private javax.swing.JButton jBGerarRelatorios;
    private javax.swing.JLabel jLUsuario;
    private javax.swing.JLabel jLUsuarioLogado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
