package interfaces;

import codigo.CodigoDistribucion;
import codigo.CodigoSeleccion;
import java.awt.Image;
import java.awt.Toolkit;

public class Distribucion extends javax.swing.JFrame {

    public Distribucion() {
        initComponents();
        setTitle("Distribución " + CodigoSeleccion.distribucion + " - Dispel");
        CodigoDistribucion codigoDistribucion = new CodigoDistribucion(labelTitulo, labelMinimo, labelMaximo, labelAlfa, labelBeta,
                txtMinimo, txtMaximo, txtAlfa, txtBeta);
        codigoDistribucion.Etiquetas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtAlfa = new javax.swing.JTextField();
        txtBeta = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        labelMinimo = new javax.swing.JLabel();
        labelMaximo = new javax.swing.JLabel();
        labelAlfa = new javax.swing.JLabel();
        labelBeta = new javax.swing.JLabel();
        txtMinimo = new javax.swing.JTextField();
        txtMaximo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        txtAlfa.setBackground(new java.awt.Color(51, 51, 51));
        txtAlfa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtAlfa.setForeground(new java.awt.Color(255, 255, 255));
        txtAlfa.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAlfa.setBorder(null);

        txtBeta.setBackground(new java.awt.Color(51, 51, 51));
        txtBeta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtBeta.setForeground(new java.awt.Color(255, 255, 255));
        txtBeta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBeta.setBorder(null);

        jButton1.setBackground(new java.awt.Color(153, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Aceptar");
        jButton1.setBorder(null);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar");
        jButton2.setBorder(null);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitulo.setText("Distribución");

        labelMinimo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelMinimo.setForeground(new java.awt.Color(255, 255, 255));
        labelMinimo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMinimo.setText("Mínimo");

        labelMaximo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelMaximo.setForeground(new java.awt.Color(255, 255, 255));
        labelMaximo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMaximo.setText("Máximo");

        labelAlfa.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelAlfa.setForeground(new java.awt.Color(255, 255, 255));
        labelAlfa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAlfa.setText("Alfa");

        labelBeta.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        labelBeta.setForeground(new java.awt.Color(255, 255, 255));
        labelBeta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBeta.setText("Beta");

        txtMinimo.setBackground(new java.awt.Color(51, 51, 51));
        txtMinimo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtMinimo.setForeground(new java.awt.Color(255, 255, 255));
        txtMinimo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMinimo.setBorder(null);

        txtMaximo.setBackground(new java.awt.Color(51, 51, 51));
        txtMaximo.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtMaximo.setForeground(new java.awt.Color(255, 255, 255));
        txtMaximo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMaximo.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(labelMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(labelBeta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(txtBeta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBeta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAlfa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBeta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // boton cancelar    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        CodigoSeleccion codigoSeleccion = new CodigoSeleccion();
    }//GEN-LAST:event_jButton2ActionPerformed

    // boton aceptar    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // valores de entrada
        double minimo = Double.parseDouble(txtMinimo.getText());
        double maximo = Double.parseDouble(txtMaximo.getText());
        double alfa = Double.parseDouble(txtAlfa.getText());
        double beta = Double.parseDouble(txtBeta.getText());
        this.setVisible(false);

        CodigoDistribucion codigoDistribucion = new CodigoDistribucion(minimo, maximo, alfa, beta);
        codigoDistribucion.GenerarArreglosBasicos();
    }//GEN-LAST:event_jButton1ActionPerformed
  @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/dado_logo.png"));
        return retValue;
    }
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
            java.util.logging.Logger.getLogger(Distribucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Distribucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Distribucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Distribucion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Distribucion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelAlfa;
    private javax.swing.JLabel labelBeta;
    private javax.swing.JLabel labelMaximo;
    private javax.swing.JLabel labelMinimo;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextField txtAlfa;
    private javax.swing.JTextField txtBeta;
    private javax.swing.JTextField txtMaximo;
    private javax.swing.JTextField txtMinimo;
    // End of variables declaration//GEN-END:variables

}
