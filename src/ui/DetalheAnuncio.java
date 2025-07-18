/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ui;

import java.util.List;
import model.Anuncio;
import model.Comprador;
import model.Lance;
import model.Sistema;

/**
 *
 * @author vitor
 */
public class DetalheAnuncio extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DetalheAnuncio.class.getName());
    private Anuncio anuncio;
    private java.awt.Frame parent;

    /**
     * Creates new form DetalheAnuncio
     */
    public DetalheAnuncio(java.awt.Frame parent, boolean modal, Anuncio anuncio) {
        super(parent, modal);
        initComponents();
        this.parent = parent;
        nomeField.setText(anuncio.getNome());
        valorField.setText(Double.toString(anuncio.getValorInicial()));
        valorAtualField.setText(Double.toString(anuncio.getValorAtual()));
        descField.setText(anuncio.getDescricao());
        vendedorField.setText(anuncio.getVendedor().getNome());
        darLanceButton.setVisible(false);
        this.anuncio = anuncio;
        if(Sistema.getInstance().isLogged()){
            if(Sistema.getInstance().getUsuarioLogado() instanceof Comprador){
                if(!anuncio.isEncerrado()){
                    darLanceButton.setVisible(true);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        valor_inicial_label = new javax.swing.JLabel();
        nome_label = new javax.swing.JLabel();
        descricao_label = new javax.swing.JLabel();
        nome_vendedor_label = new javax.swing.JLabel();
        valorField = new javax.swing.JTextField();
        nomeField = new javax.swing.JTextField();
        descField = new javax.swing.JTextField();
        vendedorField = new javax.swing.JTextField();
        darLanceButton = new javax.swing.JButton();
        valor_inicial_label1 = new javax.swing.JLabel();
        valorAtualField = new javax.swing.JTextField();
        historicoLances = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        valor_inicial_label.setText("Valor Inicial:");

        nome_label.setText("Nome:");

        descricao_label.setText("Descrição:");

        nome_vendedor_label.setText("Nome do Vendedor:");

        valorField.setEditable(false);
        valorField.setText("jTextField1");

        nomeField.setEditable(false);
        nomeField.setText("jTextField1");

        descField.setEditable(false);
        descField.setText("jTextField1");

        vendedorField.setEditable(false);
        vendedorField.setText("jTextField1");

        darLanceButton.setText("Dar Lance");
        darLanceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                darLanceButtonActionPerformed(evt);
            }
        });

        valor_inicial_label1.setText("Valor Atual:");

        valorAtualField.setEditable(false);
        valorAtualField.setText("jTextField1");

        historicoLances.setText("Histórico Lances");
        historicoLances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historicoLancesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(nome_vendedor_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(vendedorField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descricao_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(descField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nome_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(valor_inicial_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(valorField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valor_inicial_label1)
                            .addComponent(historicoLances, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valorAtualField, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(darLanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)))))
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valor_inicial_label)
                    .addComponent(valorField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome_label)
                    .addComponent(nomeField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descricao_label)
                    .addComponent(descField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nome_vendedor_label)
                    .addComponent(vendedorField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valor_inicial_label1)
                    .addComponent(valorAtualField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(darLanceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historicoLances, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void darLanceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_darLanceButtonActionPerformed
        // TODO add your handling code here:
        DarLance darLance = new DarLance(parent, true, anuncio);
        darLance.setVisible(true);
        
        valorAtualField.setText(Double.toString(anuncio.getValorAtual()));
    }//GEN-LAST:event_darLanceButtonActionPerformed

    private void historicoLancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historicoLancesActionPerformed
        // TODO add your handling code here:
        List<Lance> lances = anuncio.getLances();
        
        ListarLances listar = new ListarLances(parent, true, lances);
        listar.setLocationRelativeTo(this);
        listar.setVisible(true);
    }//GEN-LAST:event_historicoLancesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton darLanceButton;
    private javax.swing.JTextField descField;
    private javax.swing.JLabel descricao_label;
    private javax.swing.JButton historicoLances;
    private javax.swing.JTextField nomeField;
    private javax.swing.JLabel nome_label;
    private javax.swing.JLabel nome_vendedor_label;
    private javax.swing.JTextField valorAtualField;
    private javax.swing.JTextField valorField;
    private javax.swing.JLabel valor_inicial_label;
    private javax.swing.JLabel valor_inicial_label1;
    private javax.swing.JTextField vendedorField;
    // End of variables declaration//GEN-END:variables
}
