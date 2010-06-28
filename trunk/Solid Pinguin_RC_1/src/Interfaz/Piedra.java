/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Logica.ListaCircularImagenes;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Grupo_05
 */
public class Piedra extends Nodo implements Runnable {

    private ListaCircularImagenes ListaImagenes;
    /**
     * codigos de acción de las piedras
     *      false = la piedra esta quieta o sujeta a algo
     *      true = la piedra esta cayendo
     */
    private boolean Cayendo;

    public Piedra() {
        super(7);
        this.Cayendo = false;
        this.Actualizar = true;
    }

    public void setImagenes(ListaCircularImagenes lista) {
        this.ListaImagenes = lista;
    }

    @Override
    public void paint(Graphics g) {
        // se usa Graphics2D porque la calidad de imagen es mejor
        Graphics2D g2D = (Graphics2D) g;
        Image imagen = getImage();
        if (this.Actualizar) {
            if (buffered) {
                // se crea un buffer de imagen de tamaño igual al canvas, obtiene la propiedad de canvas
                // de largo y ancho para crear el buffer del mismo tamaño
                BufferedImage bi = (BufferedImage) createImage(getWidth(), getHeight());

                // Draw into the memory buffer. Dibuja cada columna

                for (int i = 0; i < getWidth(); i = i + imagen.getWidth(this)) {

                    for (int j = 0; j < getHeight(); j = j + imagen.getHeight(this)) {

                        bi.createGraphics().drawImage(imagen, i, j, this);

                    }
                }
                // Draw the buffered Image on to the screen
                g2D.drawImage(bi, 0, 0, this);
            } // This block of code draws the texture directly onto the screen.
            else if (!buffered) {
                // carga la imgagen con el método g2D.drawImage(Imagen,x,y,el observador)
                for (int i = 0; i < getWidth(); i = i + imagen.getWidth(this)) {
                    for (int j = 0; j < getHeight(); j = j + imagen.getHeight(this)) {
                        g2D.drawImage(imagen, i, j, this);
                    }
                }
            }
        } else {
            g2D.clearRect(0, 0, getWidth(), getHeight());
        }
    }

    public Image getImage() {
        return this.ListaImagenes.getNext();
    }

    private int TerminoY;

    public void run() {
        int dy = this.getY();
        while (dy < TerminoY) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException IE) {
                // ignore
            }
            this.setLocation(this.getX(), dy);
            this.repaint();
            dy = dy + 1;
        }
        this.Cayendo = false;
    }

    public void setTerminoY(int TerminoY) {
        this.TerminoY = TerminoY;
    }

    public boolean isCayendo() {
        return Cayendo;
    }
 
}
