/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Menuprincipal.java
 *
 * Created on 08-06-2010, 08:59:00 PM
 */
package Interfaz;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vladimir
 */
public class Menuprincipal extends javax.swing.JFrame {
private MenuPrinc panelPrincipal;
private MenuUser panelUsuario;
private UsuarioPrinc panelUsuarioPrinc;
private String Usuario;

    /** Creates new form Menuprincipal */
    public Menuprincipal() throws IOException, Exception {
        initComponents();
        this.setResizable(false);
        panelPrincipal = new MenuPrinc(this);
        panelUsuario = new MenuUser(this);
        panelUsuarioPrinc = new UsuarioPrinc(this,Usuario);
        panelPrincipal.setBackground(new File("Imagenes/Menu/MenuPrincipal_0.jpg"));
        this.setLayout(new BorderLayout());
        this.add(panelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

    }

    public void CargarUsuario() throws IOException {
        this.remove(panelPrincipal);
        panelUsuario.setBackground(new File("Imagenes/Menu/MenuPrincipal_0.jpg"));
        this.setLayout(new BorderLayout());
        this.add(panelUsuario, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        repaint();
    }
    public void CargarMenuPrinc() throws IOException {
        this.remove(panelUsuarioPrinc);
        panelPrincipal.setBackground(new File("Imagenes/Menu/MenuPrincipal_0.jpg"));
        this.setLayout(new BorderLayout());
        this.add(panelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        repaint();
    }
    public void CargarMenuPrincDesdeMenuUser() throws IOException {
        this.remove(panelUsuario);
        panelPrincipal.setBackground(new File("Imagenes/Menu/MenuPrincipal_0.jpg"));
        this.setLayout(new BorderLayout());
        this.add(panelPrincipal, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        repaint();
    }
    public void CargarUsuarioPrinc(String User) throws IOException {
        this.Usuario=User;
        this.remove(panelPrincipal);
        panelUsuarioPrinc.setBackground(new File("Imagenes/Menu/MenuPrincipal_0.jpg"));
        this.setLayout(new BorderLayout());
        this.add(panelUsuarioPrinc, BorderLayout.CENTER);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanel_principal = new javax.swing.JPanel();

        javax.swing.GroupLayout JPanel_principalLayout = new javax.swing.GroupLayout(JPanel_principal);
        JPanel_principal.setLayout(JPanel_principalLayout);
        JPanel_principalLayout.setHorizontalGroup(
            JPanel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
        );
        JPanel_principalLayout.setVerticalGroup(
            JPanel_principalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    new Menuprincipal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Menuprincipal.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Menuprincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel_principal;
    // End of variables declaration//GEN-END:variables

    void insertarUser(String _User) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
