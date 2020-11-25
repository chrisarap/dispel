package interfaces;

import codigo.CodigoFactorVolumetrico;
import java.awt.Image;
import java.awt.Toolkit;

public class FactorVolumetrico extends javax.swing.JFrame {

    public FactorVolumetrico() {
        initComponents();
        setTitle("Cálculo del factor volumétrico - Dispel");
        CodigoFactorVolumetrico cfv = new CodigoFactorVolumetrico(lblUno, lblDos, lblTres, lblCuatro);
        cfv.CondicionEtiqueta();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTres = new javax.swing.JLabel();
        lblCuatro = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        txtUno = new javax.swing.JTextField();
        txtDos = new javax.swing.JTextField();
        txtTres = new javax.swing.JTextField();
        txtCuatro = new javax.swing.JTextField();
        lblUno = new javax.swing.JLabel();
        lblDos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        lblTres.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblTres.setForeground(new java.awt.Color(255, 255, 255));
        lblTres.setText("tres");

        lblCuatro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblCuatro.setForeground(new java.awt.Color(255, 255, 255));
        lblCuatro.setText("cuatro");

        botonAceptar.setBackground(new java.awt.Color(153, 0, 0));
        botonAceptar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        botonAceptar.setForeground(new java.awt.Color(255, 255, 255));
        botonAceptar.setText("Aceptar");
        botonAceptar.setBorder(null);
        botonAceptar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAceptar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.png"))); // NOI18N
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonCancelar.setBackground(new java.awt.Color(153, 0, 0));
        botonCancelar.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        botonCancelar.setForeground(new java.awt.Color(255, 255, 255));
        botonCancelar.setText("Cancelar");
        botonCancelar.setBorder(null);
        botonCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCancelar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.png"))); // NOI18N
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        txtUno.setBackground(new java.awt.Color(51, 51, 51));
        txtUno.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtUno.setForeground(new java.awt.Color(255, 255, 255));
        txtUno.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtUno.setBorder(null);

        txtDos.setBackground(new java.awt.Color(51, 51, 51));
        txtDos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtDos.setForeground(new java.awt.Color(255, 255, 255));
        txtDos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDos.setBorder(null);

        txtTres.setBackground(new java.awt.Color(51, 51, 51));
        txtTres.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtTres.setForeground(new java.awt.Color(255, 255, 255));
        txtTres.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTres.setBorder(null);

        txtCuatro.setBackground(new java.awt.Color(51, 51, 51));
        txtCuatro.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        txtCuatro.setForeground(new java.awt.Color(255, 255, 255));
        txtCuatro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCuatro.setBorder(null);

        lblUno.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblUno.setForeground(new java.awt.Color(255, 255, 255));
        lblUno.setText("uno");

        lblDos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblDos.setForeground(new java.awt.Color(255, 255, 255));
        lblDos.setText("dos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblUno, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtUno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblDos, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtDos, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTres, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtTres, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtCuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTres, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCuatro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // boton cancelar
    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonCancelarActionPerformed
  @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("imagenes/dado_logo.png"));
        return retValue;
    }
    // boton aceptar
    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        // obtener valores de los campos de textos
        double uno = Double.parseDouble(txtUno.getText()),
                dos = Double.parseDouble(txtDos.getText()),
                tres = Double.parseDouble(txtTres.getText()),
                cuatro = Double.parseDouble(txtCuatro.getText());

        CodigoFactorVolumetrico cfv = new CodigoFactorVolumetrico(uno, dos, tres, cuatro);
        cfv.CondicionCalculo();
        this.setVisible(false);
    }//GEN-LAST:event_botonAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(FactorVolumetrico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FactorVolumetrico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FactorVolumetrico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FactorVolumetrico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FactorVolumetrico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCuatro;
    private javax.swing.JLabel lblDos;
    private javax.swing.JLabel lblTres;
    private javax.swing.JLabel lblUno;
    private javax.swing.JTextField txtCuatro;
    private javax.swing.JTextField txtDos;
    private javax.swing.JTextField txtTres;
    private javax.swing.JTextField txtUno;
    // End of variables declaration//GEN-END:variables
}
