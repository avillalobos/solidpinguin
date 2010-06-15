/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Juego.java
 *
 * Created on 08-06-2010, 08:20:26 AM
 */
package Interfaz;

import Logica.Estadisticas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andrew
 */
public class Juego extends javax.swing.JFrame {

    private ControladorInterfaz ControladorInterfaz;

    /** Creates new form Juego */
    public Juego() throws Exception {
        initComponents();
        this.ControladorInterfaz = new ControladorInterfaz(this);
        this.ControladorInterfaz.CargarImagenesFrame();

        this.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent evt) {
            System.out.println("Mouse entered... will try to bring focus to me...");
            SingletonFrames.getInstance().setJuegoFocus();
          }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DesplegarEstadisticas = new javax.swing.JDialog();
        BotonDeAccion = new javax.swing.JButton();
        Nivel = new javax.swing.JLabel();
        Vidas = new javax.swing.JLabel();
        Joyas = new javax.swing.JLabel();
        Salir = new javax.swing.JButton();

        BotonDeAccion.setText("jButton1");
        BotonDeAccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BotonDeAccionMouseClicked(evt);
            }
        });

        Nivel.setText("jLabel1");

        Vidas.setText("jLabel2");

        Joyas.setText("jLabel3");

        Salir.setText("jButton1");

        javax.swing.GroupLayout DesplegarEstadisticasLayout = new javax.swing.GroupLayout(DesplegarEstadisticas.getContentPane());
        DesplegarEstadisticas.getContentPane().setLayout(DesplegarEstadisticasLayout);
        DesplegarEstadisticasLayout.setHorizontalGroup(
            DesplegarEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesplegarEstadisticasLayout.createSequentialGroup()
                .addGroup(DesplegarEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DesplegarEstadisticasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Nivel))
                    .addGroup(DesplegarEstadisticasLayout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(BotonDeAccion)
                        .addGap(30, 30, 30)
                        .addComponent(Salir))
                    .addGroup(DesplegarEstadisticasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Vidas))
                    .addGroup(DesplegarEstadisticasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Joyas)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        DesplegarEstadisticasLayout.setVerticalGroup(
            DesplegarEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesplegarEstadisticasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Nivel)
                .addGap(32, 32, 32)
                .addComponent(Vidas)
                .addGap(31, 31, 31)
                .addComponent(Joyas)
                .addGap(40, 40, 40)
                .addGroup(DesplegarEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonDeAccion)
                    .addComponent(Salir))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        try {
            DesplegarDatos(this.ControladorInterfaz.MoverPersonaje(evt.getKeyCode()));
        } catch (IOException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formKeyPressed

    private void DesplegarDatos(boolean flag) {
        if (flag) {
            this.DesplegarEstadisticas.setEnabled(true);
            this.DesplegarEstadisticas.setVisible(true);
            this.DesplegarEstadisticas.setSize(400, 300);
            
            Estadisticas estadisticas = this.ControladorInterfaz.getEstadisticas();
            this.Nivel.setText("El nivel actual es = " + (estadisticas.getNivelAlcanzado() + 1));
            this.Vidas.setText("Ud actualmente posee " + estadisticas.getVidas() + " vida(s) para jugar");
            this.Joyas.setText("Las joyas capturadas fueron " + estadisticas.getCantidadDeJoyasRecuperadas() + " de un total de " + estadisticas.getCantidadDeJoyasNivel());
            this.setEnabled(false);
            if (this.ControladorInterfaz.isPersonajeMuerto()) {
                this.BotonDeAccion.setText("volver a jugar");
                this.Salir.setEnabled(true);
                this.Salir.setVisible(true);
                this.Salir.setText("Salir");
            } else {
                this.Salir.setEnabled(false);
                this.Salir.setVisible(false);
                this.BotonDeAccion.setText("siguiente nivel");
            }
            
            return;
        } else {
            return;
        }
    }
    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        try {
            this.ControladorInterfaz.MoverPersonaje(0);
        } catch (IOException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ControladorInterfaz.DetenerPersonaje();
    }//GEN-LAST:event_formKeyReleased

    private void BotonDeAccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotonDeAccionMouseClicked
        this.setEnabled(true);
        
        if (this.ControladorInterfaz.isPersonajeMuerto()) {
            try {
                this.ControladorInterfaz.ResetNivel();
            } catch (Exception ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.DesplegarEstadisticas.setVisible(false);
            this.DesplegarEstadisticas.setEnabled(false);
        } else {
            try {
                this.ControladorInterfaz.AvanzarNivel();
            } catch (Exception ex) {
                Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.DesplegarEstadisticas.setVisible(false);
            this.DesplegarEstadisticas.setEnabled(false);
        }
    }//GEN-LAST:event_BotonDeAccionMouseClicked

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        System.out.println("foco ganado");
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        System.out.println("foco perdido");
    }//GEN-LAST:event_formWindowLostFocus
    public ControladorInterfaz getContI(){
        return this.ControladorInterfaz;
    }
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonDeAccion;
    private javax.swing.JDialog DesplegarEstadisticas;
    private javax.swing.JLabel Joyas;
    private javax.swing.JLabel Nivel;
    private javax.swing.JButton Salir;
    private javax.swing.JLabel Vidas;
    // End of variables declaration//GEN-END:variables
}
